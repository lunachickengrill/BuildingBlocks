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
	@XStreamAlias("customerId")
	private String customerId;

	@XStreamAlias("firstName")
	private String firstName;

	@XStreamAlias("lastName")
	private String lastName;

	@Size(min = 2, max = 100)
	@XStreamAlias("emailAddress")
	private String emailAddress;

	@Size(min = 2, max = 16)
	@XStreamAlias("password")
	private String password;

	public ServiceRequest(final String requestId, final String requestService, final String requestMethod,
			final String customerId, final String firstName, final String lastName, final String emailAdress,
			final String password) {
		super(requestId, requestService, requestMethod);
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAdress;
		this.password = password;
	}

	public ServiceRequest(final String requestId, final String requestService, final String requestMethod) {
		super(requestId, requestService, requestMethod);
	}

	public ServiceRequest() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "ServiceRequest [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", password=" + password + ", getRequestId()=" + getRequestId()
				+ ", getRequestService()=" + getRequestService() + ", getRequestMethod()=" + getRequestMethod() + "]";
	}

}
