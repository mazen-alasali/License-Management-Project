package com.datagear.goaml.license.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;

public interface LicenseRepository extends JpaRepository<License, Long>{
	

	public List<License> findTop3ByOrderByExpirationDateAsc();
	public List<License> findTop3ByOrderByCreationDateAsc();
	

	public List<License> findByApplicationName(String applicationName);
	public List<License> findByBankId(Long id);
}
