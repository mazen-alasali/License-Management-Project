package com.datagear.goaml.license.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.service.UserService;

@RestController
@CrossOrigin(origins = "*")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findById(id);	
	}
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userService.findAll();
	}
	
	@GetMapping("/users/top10userscreated")
	public List<User> getTopTenUserCreated(){
		return  userService.findTopTenUserCreated();
	}
	
	@GetMapping("/users/top10EarlyUserRegistered")
	public List<User> getTopTenEarlyUserRegistered(){
		return  userService.findTopTenEarlyUserRegistered();
	}
	
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		return userService.updateUser(user, id);
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		//ResponseEntity
		userService.deleteById(id);
	}
	
	

}
