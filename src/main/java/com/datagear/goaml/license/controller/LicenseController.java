package com.datagear.goaml.license.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.service.LicenseService;


@RestController
public class LicenseController {
	
	private static final Logger log = LoggerFactory.getLogger(LicenseController.class);

	
	@Autowired
	private LicenseService licenseService;
	
	@GetMapping("/licenses/{id}")
	public License findById(@PathVariable Long id) {
		return licenseService.findById(id);	
	}
	
	@GetMapping("/licenses")
	public List<License> getAllLicense(){
		return licenseService.findAll();
	}
	
	@GetMapping("/licenses/top10LicensesCreated")
	public List<License> getTopTenLicensesCreated(){
		return  licenseService.findTopTenlicensesCreated();
	}
	
	@GetMapping("/licenses/top10LicensesExpired")
	public List<License> getTopTenLicensesExpired(){
		return  licenseService.findTopTenLicencesExpired();
	}
	
	@GetMapping("/licenses/licenseByApplicationName/{applicationName}")
	public List<License> getlicenseByApplicationName(@PathVariable String applicationName){
		return  licenseService.findByApplicationName(applicationName);
	}
	
	@GetMapping("/licenses/licenseByBanknName/{bankName}")
	public List<License> getlicenseByBankName(@PathVariable String bankName){
		return  licenseService.findByBankName(bankName);
	}
	
	@GetMapping("/licenses/licenseByCreatedUser/{createdUser}")
	public List<License> getlicenseByCreatedUser(@PathVariable String createdUser){
		return  licenseService.findByCreatedUser(createdUser);
	}
	
	@GetMapping("/licenses/licenseByApplicationNameAndUserAndBank/{applicationName}/{userName}/(bankName}")
	public List<License> getlicenseByApplicationNameAndUserAndBank(@PathVariable String applicationName,
			@PathVariable String userName, @PathVariable String bankName){
		System.out.println("hello");
		return  licenseService.findByApplicationNameAndUserAndBank(applicationName, userName, bankName );
	}
	
	@GetMapping("/licenses/expiredLicenses")
	public List<License> getExpiredLicenses(){
		return  licenseService.findExpiredLicenses();
	}
	
	@GetMapping("/licenses/notExpiredLicenses")
	public List<License> getNotExpiredLicenses(){
		return  licenseService.findNotExpiredLicenses();
	}
	
	@PostMapping("/licenses/{userId}/{bankName}")
	public License addLicense(@RequestBody License license, @PathVariable Long userId,@PathVariable String bankName ) {
		log.info(license.toString());
		return licenseService.save(license, userId, bankName);
	}
	
	@PutMapping("licenses/{id}")
	public License updateLicense(@RequestBody License license, @PathVariable Long id) {
		return licenseService.update(license, id);
	}
	
	
	@DeleteMapping("/licenses/{id}")
	public void deleteLicense(@PathVariable Long id) {
		//ResponseEntity
		licenseService.deleteById(id);
	}
	
	
	
	
}
