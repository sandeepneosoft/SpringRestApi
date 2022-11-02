package com.example.app.controller;

import java.text.ParseException;
import java.util.List;

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
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register-new-user")
	public void registerNewUser(@RequestBody User user) {
		System.out.println(user);
		userService.registerNewUser(user);
	}
	
	@PutMapping("/edit-user/{id}")
	public boolean editUserById(@PathVariable("id") Long id, @RequestBody User user) throws ParseException {
		System.out.println(id + " " + user);
		return userService.editUserById(id, user);
	}
	
	@GetMapping("/search-user/{pincode}")
	public User searchUserByPincode(@PathVariable("pincode") int pincode) {
		System.out.println(pincode);
		return userService.searchUserByPincode(pincode);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public boolean deleteUserById(@PathVariable("id") Long id) {
		System.out.println(id);
		return userService.deleteUserById(id);
	}
	
	@GetMapping("/sort-user/{dob}/{joiningDate}")
	public List<User> sortUserByDobAndJoiningDate(@PathVariable("dob") String dob, @PathVariable("joiningDate") String joiningDate) {
		System.out.println(dob + " " + joiningDate);
		return userService.sortUserByDobAndJoiningDate(dob, joiningDate);
	}
}
