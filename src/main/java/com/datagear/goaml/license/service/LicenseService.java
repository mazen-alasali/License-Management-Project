package com.datagear.goaml.license.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	private UserService userService;
	
	@Autowired
	private BankService bankService;
	
	public long count() {
		
		return licenseRepository.count();
	}
	
	public boolean existsById(Long id) {
		
		return licenseRepository.existsById(id);
	}
//
//	public boolean findById(Long id) {
//		boolean findFlag=true;
//		try {
//			licenseRepository.findById(id);
//			
//		} catch (Exception exception) {
//			
//			exception.printStackTrace();
//			findFlag = false;
//			return findFlag;
//			
//		}
//		return findFlag;
//	}
	
	public License findById(Long id) {
		 return licenseRepository.findById(id)
			      .orElseThrow(() -> new LicenseNotFoundException(id));
	}
	
	
	public List<License> findAll() {
		List<License> licenses= new ArrayList<>();
		licenseRepository.findAll().forEach(licenses::add);
		return licenses;
	}
	
//	public List<License> findAll() {
//		HashMap<License> licenses= new ArrayList<>();
//		licenseRepository.findAll().forEach(licenses::add);
//		return licenses;
//	}
//	
//	public List<License> findByBankName(String bankName){
//		List<License> licenses= new ArrayList<>();
//		licenseRepository.findAll().forEach(licenses::add);
//		return licenses;
//	}
//	public List<License> findByCreatedUser(String createdUser) {
//		List<License> licenses= new ArrayList<>();
//		licenseRepository.findAll().forEach(licenses::add);
//		return licenses;
//		return null;
//	}
//	public List<License> findByApplicationName(String applicationName) {
//		return null;
//	}

	
//	public List<License> findTopTenLicencesExpired() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	public List<License> findTopTenlicensesCreated(){
//		// TODO Auto-generated method stub
//				return null;
//	}
//	
	public License save(License license) {		
		User user= userService.findById(license.getUser().getId());
		license.setUser(user);
		Bank bank=bankService.findById(license.getBank().getId());
		license.setBank(bank);
		return licenseRepository.save(license);
	}
	
	
	  public License updateLicense(License updatedLicense, Long id) {

	    return licenseRepository.findById(id)
	      .map(license -> {	    	  
	       license.setName(updatedLicense.getName());
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
	
	public void deleteAll() {
		licenseRepository.deleteAll();
		
	}


}
