package com.datagear.goaml.license.repository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datagear.goaml.license.model.Bank;
import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;


public interface LicenseRepository extends JpaRepository<License, Long>{
	

	public List<License> findTop3ByOrderByExpirationDateAsc();
	public List<License> findTop3ByOrderByCreationDateAsc();
	

	public List<License> findByApplicationName(String applicationName);
	public List<License> findByBankId(Long id);
	
	public List<License> findByExpirationDateBefore(Date currentDate);
	public List<License> findByExpirationDateAfter(Date currentDate);

//	@Query("SELECT * FROM licenseDB.license l \n" + 
//			"Inner JOIN licenseDB.user u ON l.user_id= :userId\n" + 
//			"Inner JOIN licenseDB.bank b ON l.bank_id= :bankId\n" + 
//			"and l.application_name= :applicationName")
//	public List<License> findByApplicationNameAndUserIdAndBankId(
//			@Param("applicationName") String applicationName,
//			@Param("userId")  Long userId,
//			@Param("bankId")  Long bankId);
}
