package com.components.xmlservlet.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ServiceRequest")
public class ServiceRequest extends ServiceMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4339730072088544628L;

	@NotNull
	@Size(min = 2, max = 100)
	@XStreamAlias("emailAddress")
	private String emailAddress;

	@NotNull
	@Size(min = 2, max = 16)
	@XStreamAlias("password")
	private String password;

	public ServiceRequest(final String requestId, final String requestService, final String requestMethod,
			final String emailAdress, final String password) {
		super(requestId, requestService, requestMethod);
		this.emailAddress = emailAdress;
		this.password = password;
	}

	public ServiceRequest() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ServiceRequest [emailAddress=" + emailAddress + ", password=" + password + ", getRequestId()="
				+ getRequestId() + ", getRequestService()=" + getRequestService() + ", getRequestMethod()="
				+ getRequestMethod() + "]";
	}

}
