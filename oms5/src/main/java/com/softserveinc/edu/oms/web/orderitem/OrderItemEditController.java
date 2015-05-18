package com.softserveinc.edu.oms.web.orderitem;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.ParameterNames;
import com.softserveinc.edu.oms.web.orderitem.model.OrderItemsLinesNumberModel;
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
public class OrderItemEditController extends OrderItemControllerUtil {
    
    	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "orderItemsError.htm", method = RequestMethod.GET)
	public String listOrderItemError(final HttpServletRequest request, final ModelMap modelMap) {
		modelMap.put(OrderItemParameters.ERROR_MESSAGE,request.getParameter(OrderItemParameters.ERROR_MESSAGE));
		return "listError";
	}

	@RequestMapping(value = "orderItemsOpen.htm")
	public String orderItemsOpen(final HttpServletRequest request,final Locale locale, final ModelMap modelMap) {
		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));
		Integer page = getPageNumberFromRequest(request);
		User user = getLoggedUser();
		ITemporaryOrderData orderData = addOrderData(request, orderId);
		
		return redirectUrl(orderData.getTempOrder().getId(), page);
	}

	@RequestMapping(value = "orderItems.htm", method = RequestMethod.GET)
	public String listOfOrderItems(final HttpServletRequest request, 
			final Locale locale, 
			final ModelMap modelMap,
			RedirectAttributes redirectAttributes) {
		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));
		Integer page = getPageNumberFromRequest(request);
		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(request, orderId);
		User user = getLoggedUser();
		
		/*if (user.getRole().getId().equals(roleService.getAdminRole().getId())) {
			return redirectToOrderView(orderId, page);
		}*/
		if (user.getId().equals(orderData.getOrder().getCustomer().getId())) {
			if (!canOrderBeEdited(orderData.getOrder())) {
				orderData.setIsEditable(false);
				return redirectToOrderView(orderId, page);
			}
		} else if (user.getId().equals(orderData.getOrder().getAssigne().getId())) {
		    if (!canOrderBeEditedByMerchandiser(orderData.getOrder())) {
			orderData.setIsEditable(false);
			return redirectToOrderView(orderId, page);
		    }
		} else {
		    	redirectAttributes.addFlashAttribute(OrderItemParameters.ERROR_MESSAGE, 
		    		context.getMessage("order.noAccessToOrder", null, LocaleContextHolder.getLocale())
		    		+ orderData.getOrder().getOrderName());
			return "redirect:order.htm";
		}
		orderData.setIsEditable(true);
		fillModelMap(request, modelMap, orderData, locale);
		List<User> merchandiserUsers = userService.findMerchandiserUsers();
		modelMap.put(OrderItemParameters.MERCHANDISER_USERS, merchandiserUsers);
		if (orderData.getOrder().getAssigne() != null) {
			modelMap.put(OrderItemParameters.ASSIGNEE, orderData.getOrder().getAssigne().getLogin());
		}
		if (orderData.getOrder().getOrderNumber() != 0) {
			modelMap.put(OrderItemParameters.ORDER_NUMBER, orderData.getOrder().getOrderNumber());
		} else {
		    modelMap.put(OrderItemParameters.ORDER_NUMBER, orderService.getMaxOrderNumber() + 1);
		}
		return "listOrderItems";
	}

	@RequestMapping(value = "orderItemsResize.htm", method = RequestMethod.GET)
	public String resizeOrderItems(@RequestParam("orderId") Integer orderId, final HttpServletRequest request, final ModelMap modelMap) {
		OrderItemsLinesNumberModel linesNumber = (OrderItemsLinesNumberModel) request.getSession().getAttribute(OrderItemParameters.NUMBER_OF_ROWS);
		String oldPageSize = linesNumber.getPageSize();
		Integer PAGE_MODE_1 = OrderItemsLinesNumberModel.DEFAULT_PAGE_SIZE;
		Integer PAGE_MODE_2 = OrderItemsLinesNumberModel.DEFAULT_PAGE_SIZE_CHANGE;
		if (oldPageSize.equals(PAGE_MODE_1.toString())) {
		    linesNumber.setPageSize(PAGE_MODE_2.toString());
		    linesNumber.setPageSizeChange(PAGE_MODE_1.toString());
		} else {
		    linesNumber.setPageSize(PAGE_MODE_1.toString());
		    linesNumber.setPageSizeChange(PAGE_MODE_2.toString());
		}
		return redirectUrl(orderId, 0);
	}

	@RequestMapping(value = "orderItemsCreate.htm", method = RequestMethod.GET)
	public String createNewOrder(final HttpServletRequest request, final ModelMap modelMap) {
		User user = SessionExplorer.getLoggedUser();
		if (!user.getRole().getId()
				.equals(roleService.getCustomerRole().getId())) {
			return "redirect:orderItemsError.htm?"
					+ OrderItemParameters.ERROR_MESSAGE + "="
					+ "only customer can make orders";
		}
		TemporaryListOrderData listOrderData = SessionExplorer.getTemporaryListData(request);
		ITemporaryOrderData orderData = listOrderData.addOrderData();
		orderData.getOrder().setCustomer(user);
		orderData.getOrder().setTotalPrice(0.);
		return redirectUrl(orderData.getTempOrder().getId(), 0);
	}

	@RequestMapping(value = "orderItemsDelete.htm", method = RequestMethod.POST)
	public String deleteOrderItem(final HttpServletRequest request,final ModelMap modelMap) {
		Integer orderId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ID));
		Integer orderItemId = Integer.parseInt(request.getParameter(ParameterNames.ORDER_ITEM_ID));
		ITemporaryOrderData orderData = SessionExplorer.getTemporaryOrderData(request, orderId);
		orderData.remove(orderItemId);
		OrderItemsPageModel pageModel = getOrderItemPageModel(request, orderId, getOrderItemsLinesNumberModel(request));
		pageModel.validatePageNumber();
		return redirectUrl(orderId, pageModel.getCurrentPage());
	}

	private Boolean canOrderBeEdited(final Order order) {
		OrderStatus pendingStatus = orderStatusService.getPendingStatus();
		OrderStatus deliveredStatus = orderStatusService.getDeliveredStatus();
		OrderStatus orderedStatus = orderStatusService.getOrderedStatus();
		if (order.getOrderStatus() == null) {
			return true;
		}
		if (order.getOrderStatus().getId().equals(pendingStatus.getId())
				|| order.getOrderStatus().getId().equals(deliveredStatus.getId())
				|| order.getOrderStatus().getId().equals(orderedStatus.getId()) ) {
			return false;
		}
		return true;

	}
	
	private Boolean canOrderBeEditedByMerchandiser(final Order order) {
		OrderStatus deliveredStatus = orderStatusService.getDeliveredStatus();
		if (order.getAssigne().getRole().getId().equals(roleService.getMerchandiserRole().getId())
	    		&& (!order.getOrderStatus().getId().equals(deliveredStatus.getId()))) {
		    return true;
		}
		return false;
	}
}
