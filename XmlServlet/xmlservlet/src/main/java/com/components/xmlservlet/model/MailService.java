package com.components.xmlservlet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_MAILSERVICE")
public class MailService extends AbstractBaseEntity {

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "PASSWORD")
	private String password;

	public MailService(final String emailAddress, final String password) {
		this.emailAddress = emailAddress;
		this.password = password;
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
		return "MailService [emailAddress=" + emailAddress + ", password=" + password + "]";
	}

	MailService() {

	}

}
