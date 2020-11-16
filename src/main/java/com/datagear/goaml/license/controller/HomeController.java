package com.datagear.goaml.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datagear.goaml.license.model.User;
import com.datagear.goaml.license.service.UserService;

@RestController
public class HomeController {

	
	@Autowired
	private UserService userService;
	
	
//	@GetMapping("home/signin/{userName}/{password}")
//	public User signIn(@PathVariable String userName, @PathVariable String password) {
//		return userService.checkUserLogin(userName, password);
//	}
}
