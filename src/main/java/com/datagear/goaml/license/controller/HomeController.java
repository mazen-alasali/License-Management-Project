package com.datagear.goaml.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.service.UserService;

@RestController
public class HomeController {

	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("home/signin/{userName}/{password}")
	public User signIn(@PathVariable String userName, @PathVariable String password) {
		System.out.println("Username:"+ userName);
		return userService.checkUserLogin(userName, password);
	}
	
	@GetMapping("home/forgetPassword/{userName}")
	public User forgetPassword(@PathVariable String userName) {
		return userService.findByUserName(userName);
	}
	
	@GetMapping("home/resetPassword/{userName}/{password}/{newPassword}")
	public User resetPassword(@PathVariable String userName,
			@PathVariable String password, @PathVariable String newPassword) {
		return userService.resetPassword(userName, password, newPassword);
	}
	

}
