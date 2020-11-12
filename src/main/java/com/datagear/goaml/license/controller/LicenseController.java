package com.datagear.goaml.license.controller;

import java.util.List;

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
	
//	@GetMapping("/top10Licensescreated")
//	public List<License> getTopTenLicensesCreated(){
//		return  licenseService.findTopTenlicensesCreated();
//	}
	
//	@GetMapping("/top10LicensesExpired")
//	public List<License> getTopTenLicensesExpired(){
//		return  licenseService.findTopTenLicencesExpired();
//	}
	
	@PostMapping("/licenses")
	public License addLicense(@RequestBody License license) {
		return licenseService.save(license);
	}
	
	@PutMapping("licenses/{id}")
	public License updateLicense(@RequestBody License license, @PathVariable Long id) {
		return licenseService.updateLicense(license, id);
	}
	
	
	@DeleteMapping("/licenses/{id}")
	public void deleteLicense(@PathVariable Long id) {
		//ResponseEntity
		licenseService.deleteById(id);
	}
	
	
	
	
}
