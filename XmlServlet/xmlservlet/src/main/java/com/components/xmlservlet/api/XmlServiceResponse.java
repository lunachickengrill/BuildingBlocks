package com.components.xmlservlet.api;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XmlServiceResponse")
public class XmlServiceResponse extends ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8241975560458551383L;

	private static final String DEF_STATUS = "SUCCESS";

	@XStreamAlias("status")
	private String status;

	public XmlServiceResponse(final ServiceMessage request) {
//		this.requestId = request.getRequestId();
		super(request.getRequestId(), request.getRequestId());
		this.status = DEF_STATUS;
	}

	public XmlServiceResponse() {
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
		return "XmlServiceResponse [getStatus()=" + getStatus() + ", getRequestId()=" + getRequestId()
				+ ", getRequestType()=" + getRequestType() + "]";
	}

}
