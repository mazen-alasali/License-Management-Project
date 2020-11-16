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
	
	public long count() {
		
		return userRepository.count();
	}
	
	public boolean existsById(Long id) {
		
		return userRepository.existsById(id);
	}

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
		List<User> users= userRepository.findTop3ByOrderByCreationDateAsc();
//		List<User> users= userRepository.findTopTenUserCreated();
		return users;
	}
	
	public List<User> findTopTenEarlyUserRegistered() {
		List<User> users= userRepository.findTop3ByOrderByCreationDateDesc();
//		List<User> users=userRepository.findTopTenEarlyUserRegistered();
		return users;
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
//	public User findByPassword(String password) {
//		return userRepository.findByPassword(password);
//	}
	
//	public User checkUserLogin(String userName, String password) {
//	User user = userRepository.findByUserName(userName);
//	String checkName = user.getUserName();
//    String checkPassword = user.getPassword();
//
//    if (checkName.equals(userName) &&  checkPassword.equals(password)) {
//        System.out.print("Hello" + userName);
//        return user;
//    } else {
//        System.out.print("access denied");
//        return null;
//    }
//}
//
//	public String forgetPassword(String userName) {
//		return  userRepository.findByUserName(userName).getPassword();	
//	}
//
//	public User resetPassword(User userWantReset, String Password)
//	{
//		User user=userRepository.findById(userWantReset.getId());
//		 return userRepository.findById(userWantReset.getId())
//			      .map(user -> {	    	  
//			    	userWantReset.setPassword(userWantReset.getPassword()});
//			    	
//			        return userRepository.save(user);
//			      });
//		
//	}

	
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
}
