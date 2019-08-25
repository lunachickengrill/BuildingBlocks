package com.components.xmlservlet.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XmlServiceRequest")
public class XmlServiceRequest extends ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4339730072088544628L;

	@XStreamAlias("emailAddress")
	@NotNull
	@Size(min = 2, max = 100)
	private String emailAddress;

	@XStreamAlias("password")
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
		return "XmlServiceRequest [getEmailAddress()=" + getEmailAddress() + ", getPassword()=" + getPassword()
				+ ", getRequestId()=" + getRequestId() + ", getRequestType()=" + getRequestType() + "]";
	}

}
