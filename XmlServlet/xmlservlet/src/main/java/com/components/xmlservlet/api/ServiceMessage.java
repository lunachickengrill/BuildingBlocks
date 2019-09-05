package com.components.xmlservlet.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public abstract class ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520778511139533004L;

	@NotNull
	@XStreamAlias("requestId")
	private String requestId;

	@NotNull
	@XStreamAlias("requestType")
	private String requestType;

	public ServiceMessage(final String requestId, final String requestType) {
		this.requestId = requestId;
		this.requestType = requestType;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "ServiceRequest [requestId=" + requestId + ", requestMethod=" + requestType + "]";
	}

}
