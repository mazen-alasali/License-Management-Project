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

import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.repository.UserRepository;

import com.datagear.goaml.license.exception.UserNotFoundException;

@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;

//	 public User findUserById( Long id) {
//		User user= userRepository.getOne(id);
//		return user;
//	 }
	
	public User findById(Long id) {
		return userRepository.findById(id)
			      .orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public List<User> findAll() {
		List<User> users= new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	
	public List<User> findTopTenUserCreated() {
		List<User> users= userRepository.findTopTenUserCreated();
		return users;
	}
	
	public List<User> findTopTenEarlyUserRegistered() {
		List<User> users=userRepository.findTopTenEarlyUserRegistered();
		return users;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	  public User updateUser(@RequestBody User updatedUser, @PathVariable Long id) {

	    return userRepository.findById(id)
	      .map(user -> {	    	  
	       user.setPassword(updatedUser.getPassword());
	        user.setUserName(updatedUser.getUserName());
	        user.setBirthDate(updatedUser.getBirthDate());
	        return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	        updatedUser.setId(id);
	        return userRepository.save(updatedUser);
	      });
	  }
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
			
	}
	
	public void delete(User user) {
		userRepository.delete(user);
		
	}

public boolean existsById(Long id) {
		
		return userRepository.existsById(id);
	}


	
	public long count() {
		
		return userRepository.count();
	}

	public void deleteAll() {
		userRepository.deleteAll();
		
	}



	


	
//	 public List<User> findTopRecentUsers(){
//		EntityManager entityManager;
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//		Root<User> book = criteriaQuery.from(User.class);
//		List<Predicate> predicates = new ArrayList<>();
//		    
//		    if (authorName != null) {
//		        predicates.add(criteriaBuilder.equal(book.get("author"), authorName));
//		    
//		        criteriaQuery.where(predicates.toArray(new Predicate[0]));
//		 
//		    return entityManager.createQuery(criteriaQuery).getResultList();
//		}
//		 
//	 }

	
		
	
	
	

}
