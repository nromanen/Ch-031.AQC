/**
 * 
 */
package com.softserveinc.edu.oms.web;

/**
 * @author Vitalik
 * 
 */
public class ControllerRedirectErrorException extends ControllerErrorException {
	private static final long serialVersionUID = 2L;
	private String redirectUrl;

	public ControllerRedirectErrorException() {
		super("no contrpler name", "no error message");
		this.redirectUrl = "no redirect url";
	}

	public ControllerRedirectErrorException(String controlerName,
											String exceptionMessage, String redirectUrl) {
		super(controlerName, exceptionMessage);
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
}
