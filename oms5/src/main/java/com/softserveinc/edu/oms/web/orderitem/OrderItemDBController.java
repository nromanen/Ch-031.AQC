package com.softserveinc.edu.oms.web.orderitem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.CustomerType;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsPageModel;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.ITemporaryOrderData;
import com.softserveinc.edu.oms.web.orderitem.model.temporarydata.TemporaryListOrderData;
import com.softserveinc.edu.oms.web.orderitem.util.OrderItemParameters;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;

/**
 * @author Ivanka
 * 
 */
@Controller
public class OrderItemDBController extends OrderItemControllerUtil {

	private final static String ORDER_REDIRECT = "redirect:order.htm";

	@RequestMapping(value = "orderItemsSave.htm", method = RequestMethod.POST)
	public final String orderItemsSave(final HttpServletRequest request,
			final ModelMap modelMap) {

		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));

		if (!isOrderNumberValid(request)) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "="
					+ "Order Number is int value (can not be "
					+ "empty or contain symbols except numbers)";
		}

		Integer orderNumber = Integer.parseInt(request.getParameter(OrderItemParameters.ORDER_NUMBER));

		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(request, orderId);
		if (!orderData.getOrder().getOrderNumber().equals(orderNumber)
				&& orderService.orderNumberExists(orderNumber)) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "="
					+ "such order number already exists "
					+ "please enter another one";
		}

		if (orderData.getNumberOfItems().equals(0)) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "="
					+ "you can not save empty order";
		}

		String assignee = request.getParameter(OrderItemParameters.ASSIGNEE);
		if (userService.findByLogin(assignee) == null) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "=" + "pick assignee";
		}
		String deliveryDate = request.getParameter(OrderItemParameters.DELIVERY_DATE);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
		    date = formatter.parse(deliveryDate);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		String orderName = "OrderName" + orderNumber.toString();
		orderData.getOrder().setOrderName(orderName);
		orderData.getOrder().setOrderDate(new Date());
		orderData.getOrder().setPreferableDeliveryDate(date);
		orderData.getOrder().setOrderNumber(orderNumber);
		orderData.getOrder().setAssigne(userService.findByLogin(assignee));
		orderData.getOrder().setIsGift(false);
		orderData.submit(orderService, orderItemService, orderStatusService);
		OrderItemsPageModel pageModel = getOrderItemPageModel(request, orderId, getOrderItemsLinesNumberModel(request));
		pageModel.validatePageNumber();

		return redirectUrl(orderId, pageModel.getCurrentPage());
	}

	@RequestMapping(value = "orderItemsCancel.htm", method = RequestMethod.POST)
	public final String orderItemsCancel(final HttpServletRequest request, final ModelMap modelMap) {
		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));
		TemporaryListOrderData listOrderData = SessionExplorer.getTemporaryListData(request);
		listOrderData.removeOrderData(orderId);
		return ORDER_REDIRECT;
	}

	@RequestMapping(value = "orderItemsOrder.htm", method = RequestMethod.POST)
	public final String orderItemsOrder(final HttpServletRequest request, final ModelMap modelMap) {
		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));
		TemporaryListOrderData listOrderData = SessionExplorer.getTemporaryListData(request);
		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(request, orderId);
		orderData.getOrder().setOrderDate(new Date());
		orderData.getOrder().setOrderStatus(orderStatusService.getOrderedStatus());
		User user = getLoggedUser();
		double discount = user.getCustomerType().getDiscount();
		double totalPrice = orderData.getOrder().getTotalPrice();
		double maxDiscount = totalPrice * discount / 100.0;
		orderData.getOrder().setMaxDiscount((int)maxDiscount);
		user.setBalance(user.getBalance() + totalPrice - maxDiscount);
		checkCustomerType(user);
		orderService.insertOrUpdate(orderData.getOrder());
		userService.insertOrUpdate(user);
		listOrderData.removeOrderData(orderId);
		return ORDER_REDIRECT;
	}
	
	private void checkCustomerType(User user) {
	    List<CustomerType> types = customerTypeService.findAll();
	    for (CustomerType type : types) {
		if (user.getBalance() >= type.getMinRange())
		{
		    user.setCustomerType(type);
		}
	    }
	    
	}
	
}
