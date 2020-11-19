package com.datagear.goaml.license.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
	
	@Autowired
	private LicenseService licenseService;
	
	
	
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
		
		Bank bank = bankService.findByName(bankName);
		List<License> licenses = bank.getLicense();
		return licenses;	
	}

public List<License> findByCreatedUser(String createdUser) {
	
	User user = userService.findByUserName(createdUser);
	List <License> licenses = user.getLicenses();
	return licenses;	
}
	
	public List<License> findByApplicationName(String applicationName){
		List<License> licenses= licenseRepository.findByApplicationName(applicationName);
		return licenses;
		
	}
	
//	public List<License> findByApplicationNameAndUserIdAndBankId(String applicationName,Long userId, Long bankId){
//		return licenseRepository.findByApplicationNameAndUserIdAndBankId(applicationName, userId, bankId);
//	}
//
//	public List<License> findByApplicationNameAndUserAndBank(String applicationName,String  bankName, String userName){
//		Long bankId=bankService.findByName(bankName).getId();
//		long userId=userService.findByUserName(userName).getId();
//		return licenseService.findByApplicationNameAndUserAndBank(applicationName, bankName, userName);
//	}
//	@Query(value = "SELECT licenseDB.license.*\n" + 
//			"  From licenseDB.license,licenseDB.user,licenseDB.bank \n" + 
//			"  where licenseDB.license.application_name = ?1 \n" + 
//			"  and licenseDB.license.user.id = ?2 \n" + 
//			"  and  licenseDB.license.bank.id = ?3" )
//	public List<License> findByApplicationNameAndUserIdAndBankId(String applicationName,Long userId, Long bankId){
//		List<License> licenses= licenseRepository.findByApplicationNameAndUserIdAndBankId(applicationName, userId, bankId);
//		return licenses;
//	}
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
