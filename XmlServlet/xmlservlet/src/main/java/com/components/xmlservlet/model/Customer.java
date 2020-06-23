package com.components.xmlservlet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.components.xmlservlet.api.ServiceRequest;

@Entity
@Table(name = "T_CUSTOMER")
public class Customer extends AbstractBaseEntity {

	@Column(name = "CUSTOMER_ID", unique = true, updatable = true, nullable = false)
	private String customerId;

	@Column(name = "FIRSTNAME", unique = false, updatable = true, nullable = true)
	private String firstName;

	@Column(name = "LASTNAME", unique = false, updatable = true, nullable = true)
	private String lastName;

	@Column(name = "EMAIL_ADDRESS", unique = true, updatable = true, nullable = true)
	private String emailAddress;

	@Column(name = "PASSWORD", unique = false, updatable = true, nullable = true)
	private String password;

	public Customer(final String customerId) {
		this.customerId = customerId;
	}

	public static Customer fromServiceRequest(final ServiceRequest req) {
		Customer customer = new Customer();
		customer.setCustomerId(req.getCustomerId());
		customer.setFirstName(req.getFirstName());
		customer.setLastName(req.getLastName());
		customer.setEmailAddress(req.getEmailAddress());
		customer.setPassword(req.getPassword());
		return customer;
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
	public Long getId() {
		return super.getId();
	}

	@Override
	public Date getCreateDate() {
		return super.getCreateDate();
	}

	@Override
	public Date getLastModified() {
		return super.getLastModified();
	}

	@Override
	public String toStringId() {
		return super.toStringId();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", password=" + password + "]";
	}

	Customer() {

	}

}
