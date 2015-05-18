/**
 * 
 */
package com.softserveinc.edu.oms.web.order;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.Order;
import com.softserveinc.edu.oms.domain.entities.OrderStatus;
import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.service.interfaces.IOrderService;
import com.softserveinc.edu.oms.service.interfaces.IOrderStatusService;
import com.softserveinc.edu.oms.service.interfaces.IRoleService;
import com.softserveinc.edu.oms.web.order.OrderSortProperties.SortPropertiesValues;
import com.softserveinc.edu.oms.web.util.SearchModel;

/**
 * @author marko
 * 
 */

@Controller
public class OrderController {

	protected static final String SEARCH_FILTER = "searchFilter";
	protected static final String SORT_PROPERTIES = "sortProperties";
	protected static final String SEARCH_MODEL_PARAM_NAME = "searchModel";
	protected static final String PAGE_COMMAND_PARAM_NAME = "pageCommand";
	protected static final String PAGE_PARAM_NAME = "pageNumber";
	protected static final String PAGES_PARAM_NAME = "pages";
	private static final long FIRST_PAGE = 1;
	protected static final Integer PAGE_MODE_1 = SearchModel.DEFAULT_PAGE_SIZE;
	protected static final Integer PAGE_MODE_2 = SearchModel.DEFAULT_PAGE_SIZE_CHANGE;

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrderStatusService orderStatusService;

	@RequestMapping(value = "order.htm", method = RequestMethod.GET)
	public String listOrders(HttpServletRequest request, final ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request	.getSession().getAttribute(SEARCH_FILTER);
		if (searchFilterOptions == null) {
			searchFilterOptions = new SearchFilterOptions();
			SearchFilterOptions.setFilter1(this.createFilterOrder());
			SearchFilterOptions.setFilter2(this.createFilterRole());
			searchFilterOptions.setFilterBy("orderStatus");
			request.getSession().setAttribute(SEARCH_FILTER, searchFilterOptions);
		}
		
		searchFilterOptions.setAllFoundAndFiltered(orderService.getRowCount(searchFilterOptions));
		OrderSortProperties sortProperties = (OrderSortProperties) request.getSession().getAttribute(SORT_PROPERTIES);
		if (sortProperties == null) {
			sortProperties = new OrderSortProperties();
			request.getSession().setAttribute(SORT_PROPERTIES, sortProperties);
		}
		prepareData(request, map);
		map.addAttribute(SEARCH_FILTER, searchFilterOptions);
		return "order";
	}

	@RequestMapping(value = "order.htm", method = RequestMethod.POST)
	public String listFilteredOrders(
			@ModelAttribute(SEARCH_FILTER) SearchFilterOptions searchFilterOptions,
			HttpServletRequest request, final ModelMap map) {
		searchFilterOptions.setStart(0L);
		searchFilterOptions.setAllFoundAndFiltered(orderService.getRowCount(searchFilterOptions));
		prepareData(request, map);
		request.getSession().setAttribute(SEARCH_FILTER, searchFilterOptions);
		request.getSession().setAttribute(SORT_PROPERTIES, new OrderSortProperties());
		return "redirect:order.htm";
	}

	@RequestMapping("deleteOrder.htm")
	public String handleDelete(HttpServletRequest request, final HttpServletResponse arg1) {
		Integer id = Integer.parseInt(request.getParameter("orderId"));
		try {
			orderService.delete(orderService.findByID(id));
		} catch (Exception exception) {
			// TODO add something here .... to show that cannot be deleted
		}
		return "redirect:order.htm";
	}

	@RequestMapping("editOrder.htm")
	public String handleEdit(HttpServletRequest request, ModelMap map) {
		Integer id = Integer.parseInt(request.getParameter("orderID"));
		Order order = orderService.findByID(id);
		map.addAttribute("order", order);
		return "redirect:order.htm";
	}

	@RequestMapping("changePage.htm")
	public String handlePage(HttpServletRequest request, ModelMap map) {	
	    SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
	    final String pageCommand = request.getParameter(PAGE_COMMAND_PARAM_NAME);
	    
	    if ("first".equalsIgnoreCase(pageCommand)) {
		searchFilterOptions =  handleFirstPage(request, map);
	    } else if ("last".equalsIgnoreCase(pageCommand)) {
		searchFilterOptions =  handleLastPage(request, map);
	    } else if ("next".equalsIgnoreCase(pageCommand)) {
		searchFilterOptions = handleNextPage(request, map);
	    } else if ("previous".equalsIgnoreCase(pageCommand)) {
		searchFilterOptions = handlePreviousPage(request, map);
	    }
	    
	    request.getSession(true).setAttribute(SEARCH_FILTER, searchFilterOptions);
	    return "redirect:order.htm";
	}

	public SearchFilterOptions handleFirstPage(HttpServletRequest request, ModelMap map) {
	    	SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
		searchFilterOptions.setStart(0L);
		return searchFilterOptions;
	}

	public SearchFilterOptions handleLastPage(HttpServletRequest request, ModelMap map) {
	    SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
	    Integer pageSize = Integer.parseInt(searchFilterOptions.getPageSize());
	    Long size = searchFilterOptions.getAllFoundAndFiltered();
	    int lastPageSize = (int) (size % pageSize);
	    Long startPosition = size - lastPageSize;
	    if (startPosition.equals(size)) {
		startPosition -= pageSize;
	    }
	    searchFilterOptions.setStart(startPosition);
	    return searchFilterOptions;
	}

	public SearchFilterOptions handleNextPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
		Long startPosition = searchFilterOptions.getStart();
		Integer pageSize = Integer.parseInt(searchFilterOptions.getPageSize());
		startPosition += pageSize;
		Long size = searchFilterOptions.getAllFoundAndFiltered();
		if (startPosition < size) {
		    searchFilterOptions.setStart(startPosition);
		}
		return searchFilterOptions;
	}

	public SearchFilterOptions handlePreviousPage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
		Long startPosition = searchFilterOptions.getStart(); 
		Integer pageSize = Integer.parseInt(searchFilterOptions.getPageSize());
		startPosition -= pageSize;
		if (startPosition < 0) {
		    startPosition = 0L;
		}
		searchFilterOptions.setStart(startPosition);
		return searchFilterOptions;
	}
	
	@RequestMapping("resizeOrdersLisr.htm")
	public String handleResizePage(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request	.getSession().getAttribute(SEARCH_FILTER);
		String oldPageSize = searchFilterOptions.getPageSize();
		if (oldPageSize.equals(PAGE_MODE_1.toString())) {
		    searchFilterOptions.setPageSize(PAGE_MODE_2.toString());
		    searchFilterOptions.setPageSizeChange(PAGE_MODE_1.toString());
		} else {
		    searchFilterOptions.setPageSize(PAGE_MODE_1.toString());
		    searchFilterOptions.setPageSizeChange(PAGE_MODE_2.toString());
		}
		prepareData(request, map);
		return "redirect:order.htm";
	}

	private void prepareData(HttpServletRequest request, ModelMap map) {
		SearchFilterOptions searchFilterOptions = (SearchFilterOptions) request.getSession().getAttribute(SEARCH_FILTER);
		OrderSortProperties sortProperties = (OrderSortProperties) request.getSession().getAttribute(SORT_PROPERTIES);
		String sortValue = request.getParameter("propertyName");
		if ((sortValue != null) && (!sortValue.isEmpty())) {
			if (sortValue.equals("orderName")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_NAME);
			}
			if (sortValue.equals("totalPrice")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_TOTAL_PRICE);
			}
			if (sortValue.equals("maxDiscount")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_MAX_DISCOUNT);
			}
			if (sortValue.equals("deliveryDate")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_DELIVERY_DATE);
			}
			if (sortValue.equals("status")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_STATUS);
			}
			if (sortValue.equals("assignee")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_ASSIGNEE);
			}
			if (sortValue.equals("role")) {
				sortProperties.setSortOption(SortPropertiesValues.ORDER_ROLE);
			}
		}
		Integer pageSize = Integer.parseInt(searchFilterOptions.getPageSize());
		map.addAttribute("orders", orderService.find(searchFilterOptions
				.getStart().intValue(), pageSize, searchFilterOptions,
				sortProperties));
		Long page = definePage(searchFilterOptions, map);
		Long pages = countPages(searchFilterOptions, map);
		validateButtons(searchFilterOptions, page, pages);
	}
	
    private void validateButtons(final SearchFilterOptions searchModel, final Long page, final Long pages) {
	if (page.equals(FIRST_PAGE)) {
	    searchModel.setFirstPage(false);
	    searchModel.setPreviousPage(false);
	} else {
	    searchModel.setFirstPage(true);
	    searchModel.setPreviousPage(true);
	}

	if (page >= pages) {
	    searchModel.setLastPage(false);
	    searchModel.setNextPage(false);
	} else {
	    searchModel.setLastPage(true);
	    searchModel.setNextPage(true);
	}
    }
	
    protected Long definePage(final SearchFilterOptions searchModel, final ModelMap modelMap) {
	Long pageSize = Long.parseLong(searchModel.getPageSize());
	Long size = searchModel.getAllFoundAndFiltered();
	Long startPosition = searchModel.getStart();
	Long page = startPosition / pageSize + 1;
	if (startPosition > 0 && startPosition.equals(size)) {
    	    startPosition -= pageSize;
    	}
	searchModel.setStart(startPosition);
	modelMap.addAttribute(SEARCH_MODEL_PARAM_NAME, searchModel);
	modelMap.addAttribute(PAGE_PARAM_NAME, page);
	return page;
    }

    protected Long countPages(final SearchFilterOptions searchModel, final ModelMap modelMap) {
	Long size = searchModel.getAllFoundAndFiltered();
	Long pageSize = Long.parseLong(searchModel.getPageSize());
	Long pages = size / pageSize;
	if ((size % pageSize) > 0L) {
	    pages++;
	}
	if (pages.equals(0L)) {
	    pages++;
	}
	modelMap.addAttribute(PAGES_PARAM_NAME, pages);
	return pages;
    }

	private String[] createFilterOrder() {
		List<OrderStatus> list = orderStatusService.findAll();
		String[] res = new String[list.size()+1];
		res[0] = "None";
		for (int i = 1; i < res.length; i++) {
			res[i] = list.get(i-1).getOrderStatusName();
		}
		return res;
	}

	private String[] createFilterRole() {
		List<Role> list = roleService.findAll();
		String[] res = new String[list.size()+1];
		res[0] = "None";
		for (int i = 1; i < res.length; i++) {
			res[i] = list.get(i-1).getRoleName();
		}
		return res;
	}

}
