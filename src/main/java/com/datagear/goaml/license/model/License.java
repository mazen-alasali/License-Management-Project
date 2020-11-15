package com.datagear.goaml.license.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="license")
public class License {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="application_name", unique=true)
	@NotNull
	private String applicationName;
	@Column(name="price")
	@NotNull
	private double price;
	@Column(name="creation_date")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(name="expiration_date")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
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
	

//	public License(String applicationName, double price, Date expirationDate, User user, Bank bank) {
//		super();
//		this.applicationName = applicationName;
//		this.price = price;
//		this.expirationDate = expirationDate;
//		this.creationDate= creationDate;
//		this.user = user;
//		this.bank = bank;
//	}

	public License(long id, String applicationName, double price, Date creationDate, Date expirationDate, User user,
			Bank bank) {
		super();
		this.id = id;
		this.applicationName = applicationName;
		this.price = price;
		this.creationDate = creationDate;
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