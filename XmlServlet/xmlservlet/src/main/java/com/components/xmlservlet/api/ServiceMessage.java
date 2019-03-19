package com.components.xmlservlet.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520778511139533004L;

	@NotNull
	@XStreamAlias("requestId")
	private String requestId;

	@NotNull
	@XStreamAlias("requestMethod")
	private String requestMethod;

	public ServiceMessage(final String requestId, final String requestMethod) {
		this.requestId = requestId;
		this.requestMethod = requestMethod;
	}

	public ServiceMessage() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	@Override
	public String toString() {
		return "ServiceRequest [requestId=" + requestId + ", requestMethod=" + requestMethod + "]";
	}

}
