package com.components.xmlservlet.exception;

public class XmlServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public XmlServiceException(String message) {
		super(message);
	}

	public XmlServiceException(Throwable cause) {
		super(cause);
	}

}
