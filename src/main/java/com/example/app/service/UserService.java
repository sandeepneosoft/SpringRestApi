package com.example.app.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void registerNewUser(User user) {
		User savedUser = userRepository.save(user);
		System.out.println(savedUser == user);
	}
	
	public boolean editUserById(Long id, User user) throws ParseException {
		Optional<User> found = userRepository.findById(id);
		if(found.isPresent()) {
			User foundUser = found.get();
			User updatedUser = new User();
			updatedUser.setName(foundUser.getSurname());
			updatedUser.setSurname(foundUser.getSurname());
			updatedUser.setDob(foundUser.getDob());
			updatedUser.setJoiningDate(foundUser.getJoiningDate());
			updatedUser.setPincode(foundUser.getPincode());
			
			User saved = userRepository.save(updatedUser);
			
			if(saved != null)
				return true;
		} 
		return false;
	}
	
	public User searchUserByPincode(int pincode) {
		List<User> users = (List<User>) userRepository.findAll();
		for(User user: users) {
			if(user.getPincode() == pincode) {
				return user;
			}
		}
		return null;
	}
	
	public boolean deleteUserById(Long id) {
		Optional<User> found = userRepository.findById(id);
		if(found.isPresent()) {
			User foundUser = found.get();
			
			userRepository.delete(foundUser);
			return true;
		}
		return false;
	}
	
	public List<User> sortUserByDobAndJoiningDate(String dob, String joiningDate) {
		return (List<User>) userRepository.findAll(Sort.by(Direction.ASC, dob, joiningDate));
	}
}
