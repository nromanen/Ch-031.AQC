/**
 * AbstractCrudHandler
 *
 * Version 1.0
 *
 * Date 16.09.11
 *
 * Copyright Softserve
 */
package com.softserveinc.edu.oms.web.abstracthandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

/**
 * This class can be used for extending and realization of create(?), edit and
 * delete JSP.
 * 
 * @author Vitalik
 * 
 */
public abstract class AbstractCrudHandler extends AbstractHandler {
	/**
	 * This name of JSP that returns if there is no exception happen.
	 */
	private String returnJspName;

	/**
	 * This method run executeCRUDOperation method and return returnJspName if
	 * no exception happen.
	 */
	@Override
	protected final String handlerLogic(HttpServletRequest request, 	ModelMap modelMap) throws Exception {
		executeCRUDOperation(request);
		return this.returnJspName;
	}

	/**
	 * This method check returnJspName field.
	 */
	@Override
	protected void initialize() throws Exception {
		if (this.returnJspName == null) {
			throw new Exception(
					"AbstractCrudHandler must have not null \"returnJspName\" field");
		} else if (this.returnJspName.equals("")) {
			throw new Exception(
					"AbstractCrudHandler must have not empty(\"\") \"returnJspName\" field");
		}
	}

	/**
	 * This method must contain code for executing of some CRUD operation
	 * 
	 * @param request
	 *            - HTTP request object
	 * @throws Exception
	 *             if it throw exception, there is something bad happen. This
	 *             exception must catch AbstractController method "makeUpRequest"
	 */
	protected abstract void executeCRUDOperation(HttpServletRequest request)
			throws Exception;

	/**
	 * Get name of JSP that returns if there is no exception happen.
	 * 
	 * @return the jspName from this object.
	 */
	public final String getReturnJspName() {
		return returnJspName;
	}

	/**
	 * Set name of JSP that returns if there is no exception happen.
	 * 
	 * @param returnJspName
	 *            - the jspName to set.
	 */
	public final void setReturnJspName(String returnJspName) {
		this.returnJspName = returnJspName;
	}
}
