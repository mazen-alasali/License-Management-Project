package com.datagear.goaml.license.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datagear.goaml.license.model.License;

public interface LicenseRepository extends JpaRepository<License, Long>{
	
//	public List<License> findTopTenLicencesExpired();
//	public List<License>  findTopTenlicensesCreated();
	
//	public default List<License> findTopTenLicenseExpired(){
////	String sqlString = "SELECT * FROM licenseDB.license\n" + 
////			"ORDER BY expiration_date ASC LIMIT 3;";
////	Query query= entityManager.createNativeQuery(sqlString);
////	List<License> licenses=query.getResultList();
////	return licenses;	
////}
	
//	@Query(value="SELECT * FROM licenseDB.license ORDER BY creation_date DESC LIMIT 3;"
//	,nativeQuery= true )
//	public List<License> findTopTenLicenseCreated();
	
//	public List<License> findByBankName(String bankName);
//	public List<License> findByCreatedUser(String createdUser);
//	public List<License> findByApplicationName(String applicationName);

}
