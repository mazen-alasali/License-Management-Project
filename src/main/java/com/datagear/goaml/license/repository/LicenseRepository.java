package com.datagear.goaml.license.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datagear.goaml.license.model.License;

public interface LicenseRepository extends JpaRepository<License, Long>{
	

	@Query(value="SELECT * FROM licenseDB.license ORDER BY expiration_date ASC LIMIT 3;",
			nativeQuery= true)
	public List<License> findTopTenLicencesExpired();
	
	@Query(value="SELECT * FROM licenseDB.license ORDER BY creation_date Desc LIMIT 3;",
			nativeQuery= true)
	public List<License>  findTopTenlicensesCreated();
	

	public List<License> findByApplicationName(String applicationName);

	public List<License> findByBankId(Long id);
}
