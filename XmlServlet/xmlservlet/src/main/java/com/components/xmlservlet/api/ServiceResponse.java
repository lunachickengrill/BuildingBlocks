package com.components.xmlservlet.api;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XmlServiceResponse")
public class ServiceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241975560458551383L;

	private static final String DEF_STATUS = "SUCCESS";

	@XStreamAlias("requestId")
	private String requestId;

	@XStreamAlias("status")
	private String status;

	public ServiceResponse(final ServiceRequest request) {
		this.requestId = request.getRequestId();
		this.status = DEF_STATUS;
	}

	public ServiceResponse() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ServiceResponse [requestId=" + requestId + ", status=" + status + "]";
	}

}
