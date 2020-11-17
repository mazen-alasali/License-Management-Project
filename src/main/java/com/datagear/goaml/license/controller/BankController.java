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

import com.datagear.goaml.license.model.Bank;
import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.service.BankService;

@RestController
public class BankController {

	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/banks/{id}")
	public Bank findById(@PathVariable Long id) {
		return bankService.findById(id);	
	}
	
	@GetMapping("/banks")
	public List<Bank> getAllanks(){		
		try {
			return bankService.findAll();
		} catch (Exception e) {
			System.out.println("What's Happened?1 ");
			return null;
		}
		
	}
	
	@GetMapping("/banks/bankByName/{bankName}")
	public Bank getlBankByName(@PathVariable String bankName){
		return  bankService.findByName(bankName);
	}
	
	@PostMapping("/banks/{userId}")
	public Bank addBank(@RequestBody Bank bank, @PathVariable Long userId) {
		return bankService.save(bank, userId);
	}
	
	@PutMapping("banks/{id}")
	public Bank updateBank(@RequestBody Bank bank, @PathVariable Long id) {
		return bankService.updateBank(bank, id);
	}
	
	
	@DeleteMapping("/banks/{id}")
	public void deleteBank(@PathVariable Long id) {
		//ResponseEntity
		bankService.deleteById(id);
	}
	
	
}
