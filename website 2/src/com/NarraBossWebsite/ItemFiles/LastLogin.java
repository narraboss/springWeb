package com.NarraBossWebsite.ItemFiles;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class LastLogin {
	
	@Id
	int id;
	String  customerId ;
	Date date;
	
	
	public LastLogin(String customerId, Date date) {
		super();
		this.customerId = customerId;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
