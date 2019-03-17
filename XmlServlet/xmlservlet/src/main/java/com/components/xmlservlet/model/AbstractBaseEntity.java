package com.components.xmlservlet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.GenerationType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="create_date", nullable=false, updatable=true)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;
	
	@Column(name="last_modified", nullable=false, updatable=true)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModified;

	public Long getId() {
		return id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public String toStringId() {
		return id.toString();
	}

}
