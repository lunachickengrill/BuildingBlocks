package com.components.xmlservlet.exception;

public class BeanValidationException extends XmlServiceException {

	private static final long serialVersionUID = 1L;

	public BeanValidationException(final String message) {
		super(message);
	}
	
	public BeanValidationException(final Throwable throwable) {
		super(throwable);
	}

}
