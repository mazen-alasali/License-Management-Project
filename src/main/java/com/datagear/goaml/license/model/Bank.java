package com.datagear.goaml.license.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="bank")
public class Bank {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@Column(name="name", unique=true)
	@NotNull
	private String name;
	@Column(name="country")
	@NotNull
	private String country;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
	@JsonIgnore
    private User user;
	
	@OneToMany(mappedBy="bank", cascade=CascadeType.ALL)
	@JsonIgnore
    private List<License> licenses;
	
	public Bank() {
		super();
	}
	
	public Bank(@NotNull String name, @NotNull String country, User user) {
		super();
		this.name = name;
		this.country = country;
		this.user = user;
	}
	
	public Bank(@NotNull long id, @NotNull String name, @NotNull String country, User user, List<License> licenses) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.user = user;
		this.licenses = licenses;
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
	public User getUser() {
		return user;
	}
	
	public List<License> getLicense() {
		return licenses;
	}
	public void setLicense(List<License> licenses) {
		this.licenses = licenses;
	}
	
}