package com.datagear.goaml.license.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datagear.goaml.license.exception.LicenseNotFoundException;
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
			      .orElseThrow(() -> new LicenseNotFoundException(id));
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

	public License save(License license) {
		return licenseRepository.save(license);
	}
	
	
	  public License update(License updatedLicense, Long id) {

	    return licenseRepository.findById(id)
	      .map(license -> {	    	  
	       license.setApplicationName(updatedLicense.getApplicationName());
	       license.setPrice(updatedLicense.getPrice());
	       license.setExpirationDate(updatedLicense.getExpirationDate());
	       license.setBank(updatedLicense.getBank());
	       license.setUser(updatedLicense.getUser());
	        return licenseRepository.save(license);
	      })
	      .orElseGet(() -> {
	        updatedLicense.setId(id);
	        return licenseRepository.save(updatedLicense);
	      });
	  }

	public void deleteById(Long id) {
		licenseRepository.deleteById(id);
		
	}

	public void delete(License license) {
		licenseRepository.delete(license);
		
	}	
	
}
