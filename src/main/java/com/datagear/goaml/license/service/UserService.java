package com.datagear.goaml.license.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.datagear.goaml.license.model.Bank;
import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.repository.UserRepository;

import com.datagear.goaml.license.exception.HandledException;

@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	public long count() {
		
		return userRepository.count();
	}
	
	public boolean existsById(Long id) {
		
		return userRepository.existsById(id);
	}

	public User findById(Long id)  {
		return userRepository.findById(id)
				 .orElseThrow(() -> new HandledException("Method:findById, Result: user not found"));
	}
	
	public List<User> findAll() {
		List<User> users= new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	
	public List<User> findTopTenUserCreated() {
		List<User> users= userRepository.findTop3ByOrderByCreationDateAsc();
		return users;
	}
	
	public List<User> findTopTenEarlyUserRegistered() {
		List<User> users= userRepository.findTop3ByOrderByCreationDateDesc();
		return users;
	}
	
	public User findByUserName(String userName) throws HandledException  {
		try {
			return userRepository.findByUserName(userName);
		}
		catch (HandledException exception) {
		exception.printMessage("Method:findByUserName, Result: user not found");
		return null;	
		}
				
	}
	

	public User checkUserLogin(String userName, String password)  {
		return userRepository.findByUserNameAndPassword(userName, password);
}
	public User resetPassword(String userName, String password, String newPassword) {
		User user = userRepository.findByUserNameAndPassword(userName, password);
		user.setPassword(newPassword);
		user = userRepository.save(user);
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	  public User updateUser(@RequestBody User updatedUser, @PathVariable Long id) throws HandledException {
		        User user = userRepository.findById(id)
		        .orElseThrow(() -> new HandledException("User with ID :"+id+" Not Found!"));  
		        user.setPassword(updatedUser.getPassword());
				user.setUserName(updatedUser.getUserName());
				user.setBirthDate(updatedUser.getBirthDate());
				return userRepository.save(user);
	  }
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
			
	}
	
	public void delete(User user) {
		userRepository.delete(user);
		
	}

}
