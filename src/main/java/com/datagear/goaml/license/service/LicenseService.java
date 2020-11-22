package com.datagear.goaml.license.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datagear.goaml.license.exception.HandledException;
import com.datagear.goaml.license.model.Bank;
import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.repository.LicenseRepository;


@Service
public class LicenseService {
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private UserService userService;	
	
	public long count() {
		
		return licenseRepository.count();
	}
	
	public boolean existsById(Long id) {
		
		return licenseRepository.existsById(id);
	}
	
	public License findById(Long id) {
		 return licenseRepository.findById(id)
			      .orElseThrow(() -> new HandledException("Method:findById, Result: license not found"));
	}
	
	
	public List<License> findAll() {
		List<License> licenses= new ArrayList<>();
		licenseRepository.findAll().forEach(licenses::add);
		return licenses;
	}
	
	public List<License> findTopTenLicencesExpired() {
		List<License> licenses= licenseRepository.findTop3ByOrderByExpirationDateAsc();
		return licenses;
	}
	
	public List<License> findTopTenlicensesCreated(){
		List<License> licenses= licenseRepository.findTop3ByOrderByCreationDateAsc();
		return licenses;
	}
	
public List<License> findByBankName(String bankName) {
		return licenseRepository.findByBankName(bankName);
	}

public List<License> findByCreatedUser(String createdUser) {
	return licenseRepository.findByUserUserName(createdUser);	
}
	
	public List<License> findByApplicationName(String applicationName){
		List<License> licenses= licenseRepository.findByApplicationName(applicationName);
		return licenses;		
	}
	
	public List<License> findByByApplicationNameAndUserAndBank(String applicationName,
			String userName, String bankName){
		return licenseRepository.findByApplicationNameAndUserUserNameAndBankName(applicationName, userName, bankName);
	}
	
	public List<License> findExpiredLicenses(){
		Date currentDate = new Date(new java.util.Date().getTime());
		return licenseRepository.findByExpirationDateBefore(currentDate);
	}
		
	public List<License> findNotExpiredLicenses(){
		Date currentDate = new Date(new java.util.Date().getTime());
		return licenseRepository.findByExpirationDateAfter(currentDate);
	}
	
	
	public License save(License license, Long userId, String bankName) {
		User user = userService.findById(userId);
		Bank bank = bankService.findByName(bankName);
		license.setUser(user);
		license.setBank(bank);
		return licenseRepository.save(license);
	}
	
	
	  public License update(License updatedLicense, Long id) {
		  License license = licenseRepository.findById(id)
				  	.orElseThrow(() -> new HandledException("License with ID :"+id+" Not Found!"));  
		  license.setApplicationName(updatedLicense.getApplicationName());
		  license.setExpirationDate(updatedLicense.getExpirationDate());
		  return licenseRepository.save(license);
	  }

	public void deleteById(Long id) {
		licenseRepository.deleteById(id);
		
	}

	public void delete(License license) {
		licenseRepository.delete(license);
		
	}	

}
