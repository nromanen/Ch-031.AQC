/**
 * 
 */
package com.softserveinc.edu.oms.web;

/**
 * @author Vitalik
 * 
 */
public class ControllerErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String exceptionMessage;
	protected String controllerName;

	public ControllerErrorException() {
		super();
		this.exceptionMessage = "no error message";
		this.controllerName = "no controller name";
	}

	public ControllerErrorException(String controllerName, String exceptionMessage) {
		super();
		this.controllerName = controllerName;
		this.exceptionMessage = exceptionMessage;
	}

	public String toString() {
		return "Controler " + this.controllerName + ": " + this.exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
}
