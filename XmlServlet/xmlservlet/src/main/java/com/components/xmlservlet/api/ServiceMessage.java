package com.components.xmlservlet.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Baseclass for a particular message. Contains shared properties.
 * @author tschwaiger
 *
 */

public abstract class ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 520778511139533004L;

	@NotNull
	@XStreamAlias("requestId")
	private String requestId;

	@NotNull
	@XStreamAlias("requestService")
	private String requestService;

	@NotNull
	@XStreamAlias("requestMethod")
	private String requestMethod;

	public ServiceMessage(final String requestId, final String requestService, final String requestMethod) {

		this.requestId = requestId;
		this.requestService = requestService;
		this.requestMethod = requestMethod;

	}

	public ServiceMessage() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public String getRequestService() {
		return requestService;
	}

	public void setRequestService(String requestService) {
		this.requestService = requestService;
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

		return "ServiceMessage [requestId=" + requestId + ", requestService=" + requestService + ", requestMethod="
				+ requestMethod + "]";

	}

}
