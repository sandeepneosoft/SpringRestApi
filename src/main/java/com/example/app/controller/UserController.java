package com.example.app.controller;

import java.text.ParseException;
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

import com.example.app.model.User;
import com.example.app.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register-new-user")
	public void registerNewUser(@RequestBody User user) {
		logger.info("Request Payload {}", user);
		userService.registerNewUser(user);
	}
	
	@PutMapping("/edit-user/{id}")
	public boolean editUserById(@PathVariable("id") Long id, @RequestBody User user) throws ParseException {
		logger.info("editing user with {}", id, " and user {}", user);
		boolean flag = userService.editUserById(id, user);
		logger.info("edit done with flag {}", flag);
		return flag;
	}
	
	@GetMapping("/search-user/{pincode}")
	public User searchUserByPincode(@PathVariable("pincode") int pincode) {
		logger.info("searching user pincode {}", pincode);
		User user = userService.searchUserByPincode(pincode);
		logger.info("user found {}", user);
		return user;
	}
	
	@DeleteMapping("/delete-user/{id}")
	public boolean deleteUserById(@PathVariable("id") Long id) {
		logger.info("deleting user for given id {}", id);
		boolean flag = userService.deleteUserById(id);
		logger.info("deletion done with flag {}", flag);
		return flag;
	}
	
	@GetMapping("/sort-user/{dob}/{joiningDate}")
	public List<User> sortUserByDobAndJoiningDate(@PathVariable("dob") String dob, @PathVariable("joiningDate") String joiningDate) {
		logger.info("sorting user with {}", dob , " and {}", joiningDate);
		List<User> users = userService.sortUserByDobAndJoiningDate(dob, joiningDate);
		logger.info("sorting done with these users {}", users);
		return users;
	}
}
