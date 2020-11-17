package com.datagear.goaml.license.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.datagear.goaml.license.exception.HandledException;
import com.datagear.goaml.license.model.Bank;
import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private UserService userService;
	
	public long count() {
		return bankRepository.count();
	}
	
	public boolean existsById(Long id) {
		return bankRepository.existsById(id);
	}
	
	public Bank findById(Long id) {
		
		 return bankRepository.findById(id)
			      .orElseThrow(() -> new HandledException("Method:findById, Result: bank not found"));
	}

	
	public List<Bank> findAll() {
		List<Bank> banks = new ArrayList<>();
		bankRepository.findAll().forEach(banks::add);
		return banks;
	}
	
	public Bank findByName(String bankName){
		return bankRepository.findByName(bankName);
	}

	
	public Bank save(Bank bank, Long userId) {
		User user=userService.findById(userId);
		bank.setUser(user);
		return bankRepository.save(bank);
	}
	
	
	  public Bank updateBank(Bank updatedBank, Long id) throws HandledException{
		  Bank bank = bankRepository.findById(id)
				  	.orElseThrow(() -> new HandledException("Bank with ID :"+id+" Not Found!"));  
		  bank.setName(updatedBank.getName());
		  bank.setCountry(updatedBank.getCountry());
		  return bankRepository.save(bank);
		  
	  }
	  
	public void deleteById(Long id) {
		bankRepository.deleteById(id);
		
	}

	
	public void delete(Bank bank) {
		bankRepository.delete(bank);
		
	}

	
	public void deleteAll() {
		bankRepository.deleteAll();
		
	}

}
