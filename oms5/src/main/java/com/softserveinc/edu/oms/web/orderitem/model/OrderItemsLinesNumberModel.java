package com.softserveinc.edu.oms.web.orderitem.model;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * @author Ivanka
 * 
 */
public class OrderItemsLinesNumberModel {

    	public final static Integer DEFAULT_PAGE_SIZE = 5;
	public final static Integer DEFAULT_PAGE_SIZE_CHANGE = 10;
	
	private String pageSize;
	private String pageSizeChange;
	
	private SortProperties sortProperties;

	public OrderItemsLinesNumberModel() {
	    	pageSize = DEFAULT_PAGE_SIZE.toString();
		pageSizeChange = DEFAULT_PAGE_SIZE_CHANGE.toString();
		setSortProperties(new SortProperties());
	}

	public String getPageSize() {
	    return pageSize;
	}

	public void setPageSize(String pageSize) {
	    this.pageSize = pageSize;
	}

	public String getPageSizeChange() {
	    return pageSizeChange;
	}

	public void setPageSizeChange(String pageSizeChange) {
	    this.pageSizeChange = pageSizeChange;
	}

	public SortProperties getSortProperties() {
	    return sortProperties;
	}

	public void setSortProperties(SortProperties sortProperties) {
	    this.sortProperties = sortProperties;
	}

}
