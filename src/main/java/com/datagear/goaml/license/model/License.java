package com.datagear.goaml.license.model;

import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="license")
public class License {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	@Column(name="price")
	private double price;
	@Column(name="creation_date")
	private Date creationDate;
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
    private User user;
	
	@ManyToOne
    @JoinColumn(name="bank_id")
	@JsonIgnore
    private Bank bank;
	
	
	public License() {
		super();
	}
	public License(String name, double price, Date expirationDate, User user) {
	
		super();
		this.name = name;
		this.price=price;
		this.creationDate=this.getTimestampForMySql();
		this.expirationDate = expirationDate;
		this.user=user;
	}
	public License(String name, double price, Date expirationDate, User user, Bank bank ) {
		
		super();
		this.name = name;
		this.price=price;
		this.creationDate=this.getTimestampForMySql();
		this.expirationDate = expirationDate;
		this.user=user;
		this.bank=bank;
	}
	private Timestamp getTimestampForMySql() {
		long nowDate= new Date().getTime();
		Timestamp timestamp = new Timestamp(nowDate);
		return timestamp;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
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