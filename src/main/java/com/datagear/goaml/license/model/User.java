package com.datagear.goaml.license.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long id;
	@Column(name="user_name", unique=true)
	@NotNull
	private String userName;
	@Column(name="password")
	@NotNull
	private String password;
	@Column(name="creation_date")
	@NotNull
	private Date creationDate;
	@Column(name="birth_date")
	private Date birthDate;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<License> licenses;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Bank> banks;
	
	public List<License> getLicenses() {
		return licenses;
	}
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}
	public List<Bank> getBanks() {
		return banks;
	}
	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}
	public User() {
		super();
	}
	public User(String userName, String password, Date birthDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.creationDate = this.getCreationDate();
		this.birthDate = birthDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
//	public void addLicenses(License license) {
//		licenses.add(license);
//        license.setUser(this);
//	}
//	
//	public void removeLicense(License license) {
//		license.setUser(null);
//        this.licenses.remove(license);
//    }
//	
//	public void addBanks(Bank bank) {
//		banks.add(bank);
//		bank.setUser(this);
//	}
//	
//	public void removeBank(Bank bank) {
//		bank.setUser(null);
//        this.banks.remove(bank);
//    }

}
