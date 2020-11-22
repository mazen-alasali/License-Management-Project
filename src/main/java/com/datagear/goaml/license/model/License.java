package com.datagear.goaml.license.model;

import java.util.Calendar;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="license")
public class License {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="application_name")
	@NotNull
	private String applicationName;
	@Column(name="price")
	@NotNull
	private double price;
	@Column(name="creation_date")
	@NotNull
	private Date creationDate;
	@Column(name="expiration_date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date expirationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
//	@JsonIgnore
	@JsonBackReference
    private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_id",  referencedColumnName = "id")
	@JsonIgnore
    private Bank bank;
	
	public License() {
		super();
	}
	

	public License(String applicationName, double price, Date expirationDate, User user) {
		super();
		this.applicationName = applicationName;
		this.price = price;
		this.expirationDate = expirationDate;
		this.creationDate= this.getCreationDate();
		this.user = user;
	}

	public License(long id, String applicationName, double price, Date creationDate, Date expirationDate, User user,
			Bank bank) {
		super();
		this.id = id;
		this.applicationName = applicationName;
		this.price = price;
		this.creationDate = this.getCreationDate();
		this.expirationDate = expirationDate;
		this.user = user;
		this.bank = bank;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String name) {
		this.applicationName = name;
	}
	public Date getCreationDate() {
			// create a java calendar instance
			Calendar calendar = Calendar.getInstance();
			// get a java date (java.util.Date) from the Calendar instance.
			// this java date will represent the current date, or "now".
			java.util.Date currentDate = calendar.getTime();
			// now, create a java.sql.Date from the java.util.Date
			Date creationDate = new java.sql.Date(currentDate.getTime());
	
	return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
	

}