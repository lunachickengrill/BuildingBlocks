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

	public ServiceResponse(final ServiceMessage request) {
		super(request.getRequestId(), request.getRequestService(), request.getRequestMethod());
		this.status = DEF_STATUS;
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

	@Override
	public String toString() {
		return "XmlServiceResponse [status=" + status + ", getRequestId()=" + getRequestId() + ", getRequestMethod()="
				+ getRequestMethod() + "]";
	}

}
