package com.components.xmlservlet.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class XmlServiceRequest extends ServiceRequest {

	@NotNull
	@Size(min = 2, max = 100)
	private String emailAddress;

	@NotNull
	@Size(min = 2, max = 16)
	private String password;

	public XmlServiceRequest(final String requestId, final String type, final String emailAdress,
			final String password) {
		super(requestId, type);
		this.emailAddress = emailAdress;
		this.password = password;
	}

	public XmlServiceRequest() {
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
		return "XmlServiceRequest [emailAddress=" + emailAddress + ", password=" + password + ", getRequestId()="
				+ getRequestId() + ", getType()=" + getType() + "]";
	}
	
	

}
