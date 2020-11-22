package com.datagear.goaml.license.repository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.datagear.goaml.license.model.License;



public interface LicenseRepository extends JpaRepository<License, Long>{
	

	public List<License> findTop3ByOrderByExpirationDateAsc();
	public List<License> findTop3ByOrderByCreationDateAsc();
	
	public List<License> findByBankName(String bankName);
	public List<License> findByExpirationDateBefore(Date currentDate);
	public List<License> findByExpirationDateAfter(Date currentDate);
	
	public List<License> findByApplicationName(String applicationName);
	public List<License> findByUserUserName(String userName);
	public List<License> findByApplicationNameAndUserUserNameAndBankName(String applicationName,
			String userName, String bankName);
}