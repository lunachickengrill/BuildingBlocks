package com.components.xmlservlet.api;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ServiceResponse")
public class ServiceResponse extends ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241975560458551383L;

	private static final String DEF_STATUS = "SUCCESS";

	@XStreamAlias("status")
	private String status;

	@XStreamAlias("statusmessage")
	private String statusMessage;

	public ServiceResponse(final ServiceMessage request) {
		super(request.getRequestId(), request.getRequestService(), request.getRequestMethod());
		this.status = DEF_STATUS;
		this.statusMessage = "";
	}

	public ServiceResponse() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "ServiceResponse [status=" + status + ", getRequestId()=" + getRequestId() + ", getRequestService()="
				+ getRequestService() + ", getRequestMethod()=" + getRequestMethod() + "]";
	}

}
