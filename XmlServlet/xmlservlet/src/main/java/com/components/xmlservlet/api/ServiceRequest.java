package com.components.xmlservlet.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ServiceRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520778511139533004L;

	@NotNull
	private String requestId;

	@NotNull
	private String type;

	public ServiceRequest(final String requestId, final String type) {
		this.requestId = requestId;
		this.type = type;
	}

	public ServiceRequest() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ServiceRequest [requestId=" + requestId + ", type=" + type + "]";
	}

}
