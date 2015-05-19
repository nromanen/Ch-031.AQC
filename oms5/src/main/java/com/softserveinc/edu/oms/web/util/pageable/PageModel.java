/**
 * 
 */
package com.softserveinc.edu.oms.web.util.pageable;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Vitalik
 * 
 */
public class PageModel {
	// Fields
    	public final static Integer DEFAULT_PAGE_SIZE = 5;
	public final static Integer DEFAULT_PAGE_SIZE_CHANGE = 10;
	private int numberOfElements;
	private int numberOfPages;
	private int currentPage = 1;
	private String pageSize;
	private String pageSizeChange;
	//private PageShowElements showElements = PageShowElements.Five;
	private List<ParameterPair> listOfRequiredParameters;

	// Initialize methods and constructor

	/**
	 * 
	 * @param numberOfElements
	 * @param request
	 * @throws ControlerErrorException
	 */
	public PageModel(int numberOfElements, final HttpServletRequest request) throws Exception {
		this.numberOfElements = numberOfElements;
		
		//this.showElements = getShowElementsFromRequestParameter(request);
		String oldPageSize = request.getParameter("showElements");
		if (oldPageSize == null) {
		  this.pageSize = DEFAULT_PAGE_SIZE.toString();
		  this.pageSizeChange = DEFAULT_PAGE_SIZE_CHANGE.toString(); 
		}else {
		    if (oldPageSize.equals(PageModel.DEFAULT_PAGE_SIZE.toString())) {
		    	this.pageSize = DEFAULT_PAGE_SIZE_CHANGE.toString();
		    	this.pageSizeChange = DEFAULT_PAGE_SIZE.toString();
			} else {
		    	this.pageSize = DEFAULT_PAGE_SIZE.toString();
		    	this.pageSizeChange = DEFAULT_PAGE_SIZE_CHANGE.toString();
			}
		}
		this.numberOfPages = getNumberOfPages(numberOfElements);
		this.currentPage = getCurrentPageFromRequestParameter(request);
		listOfRequiredParameters = new ArrayList<ParameterPair>();
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ControlerErrorException
	 */
	private int getCurrentPageFromRequestParameter(
			final HttpServletRequest request) throws Exception {
		String currentPageParameter = request.getParameter("page");
		if (currentPageParameter == null || currentPageParameter.equals(""))
			return 1;
		else {
			int currentPageInteger = 0;
			try {
				currentPageInteger = Integer.parseInt(currentPageParameter);
			} catch (NumberFormatException e) {
				Exception exception = new Exception(
						"Can not parse \"page\" parameter from request");
				throw exception;
			}
			Integer thisPageSize = Integer.parseInt(pageSize);
			if ((currentPageInteger - 1) * thisPageSize > this.numberOfElements)
				throw new Exception("Bad page parameter, because there are "
						+ this.numberOfElements + " but current page is "
						+ currentPageInteger + " with showElements "
						+ thisPageSize);
			return currentPageInteger;
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ControlerErrorException
	 */
	/*
	private PageShowElements getShowElementsFromRequestParameter(
			final HttpServletRequest request) throws Exception {
		String showElementsParameter = request.getParameter("showElements");
		if (showElementsParameter == null || showElementsParameter == "")
			return PageShowElements.Five;
		else {
			int showElementsInteger = 0;
			try {
				showElementsInteger = Integer.parseInt(showElementsParameter);
			} catch (NumberFormatException e) {
				Exception exception = new Exception(
						"Can not parse \"showElements\" parameter from request");
				throw exception;
			}
			return PageShowElements
					.getPageShowElementsFromInteger(showElementsInteger);
		}
	}*/

	/**
	 * 
	 * @param numberOfElements
	 * @param showElements
	 * @return
	 */
	private int getNumberOfPages(int numberOfElements/*, PageShowElements showElements*/) {
	    	Integer thisPageSize = Integer.parseInt(pageSize);
		if (numberOfElements < 0)
			System.out.println("bad numberOfElements in PageModel");
		int numberOfPages = 0;
		if (numberOfElements != 0) {
			if (numberOfElements % thisPageSize == 0) {
				numberOfPages = numberOfElements / thisPageSize;
			} else {
				numberOfPages = numberOfElements / thisPageSize + 1;
			}
		}
		return numberOfPages;
	}

	// Set page date methods

	/**
	 * 
	 * @return
	 */
	public Boolean hasElementsToShow() {
		return this.numberOfElements != 0;
	}

	/**
	 * 
	 * @return
	 */
	public int selectStartPos() {
		return (this.currentPage - 1) * Integer.parseInt(pageSize);
	}

	/**
	 * 
	 * @return
	 */
	public int selectMaxElements() {
		return Integer.parseInt(pageSize);
	}

	// PageSize method

	/**
	 * 
	 * @param pageShowElement
	 * @return
	 */
	/*
	public Boolean isCurrentPageShowElement(PageShowElements pageShowElement) {
		return this.showElements == pageShowElement;
	}*/

	// PageActions method

	/**
	 * 
	 * @param pageAction
	 * @return
	 */
	public Boolean isPageActionDisabled(PageActions pageAction) {
		switch (pageAction) {
		case FirstPageAction:
		case PreviousPageAction:
			return !(this.currentPage > 1);
		case NextPageAction:
		case LastPageAction:
			return !(this.currentPage < this.numberOfPages);
		default:
			return false;
		}
	}

	// Getters and Setters

	/**
	 * 
	 * @return
	 */
	public int getNumberOfPages() {
		return this.numberOfPages;
	}

	/**
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * 
	 * @return
	 */
	/*
	public PageShowElements getShowElements() {
		return this.showElements;
	}*/

	/**
	 * 
	 * @return
	 */
	public List<ParameterPair> getListOfRequiredParameters() {
		return this.listOfRequiredParameters;
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

	/**
	 * 
	 * @param showElements
	 */
	/*
	public void setShowElements(PageShowElements showElements) {
		this.showElements = showElements;
	}*/

	/**
	 * 
	 * @param showElements
	 * @throws ControlerErrorException
	 * @throws NumberFormatException
	 */
	/*
	public void setShowElements(String newSize) throws Exception {
		this.showElements = PageShowElements
				.getPageShowElementsFromInteger(Integer.parseInt(newSize));
	}*/
	
	
}
