package com.datagear.goaml.license.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bank")
public class Bank {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	@Column(name="country")
	private String country;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
    private User user;
	
	@OneToMany(mappedBy="bank", cascade=CascadeType.PERSIST)
	@Transient
    private List<License> licenses;
	
	
	public Bank() {
		super();
	}
	public Bank(String name, String country) {
		super();
		this.name = name;
		this.country= country;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
//	public void addLicenses(License license) {
//		licenses.add(license);
//        license.setBank(this);
//	}
//	
//	public void removeLicense(License license) {
//		license.setBank(null);
//        this.licenses.remove(license);
//    }

	public List<License> getLicense() {
		return licenses;
	}
	public void setLicense(List<License> licenses) {
		this.licenses.add((License) licenses);
	}
	public User getUser() {
		return user;
	}
	
}