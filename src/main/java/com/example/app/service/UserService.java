package com.example.app.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public void registerNewUser(User user) {
		logger.debug("{}", user);
		User savedUser = userRepository.save(user);
		logger.info(savedUser == user ? "new user saved" : "not saved");
	}
	
	public boolean editUserById(Long id, User user) throws ParseException {
		logger.debug("ID {}", id, " of user {}", user);
		Optional<User> found = userRepository.findById(id);
		if(found.isPresent()) {
			User foundUser = found.get();
			logger.info("user matched with given ID {}", id);
			User updatedUser = new User();
			updatedUser.setId(foundUser.getId());
			updatedUser.setName(foundUser.getSurname());
			updatedUser.setSurname(foundUser.getSurname());
			updatedUser.setDob(foundUser.getDob());
			updatedUser.setJoiningDate(foundUser.getJoiningDate());
			updatedUser.setPincode(foundUser.getPincode());
			
			User saved = userRepository.save(updatedUser);
			logger.info(saved == updatedUser ? "existing user updated with " + id + "." : "not updated");
			if(saved != null)
				return true;
		} 
		return false;
	}
	
	public User searchUserByPincode(int pincode) {
		logger.debug("searching user with given pincode {}", pincode);
		List<User> users = (List<User>) userRepository.findAll();
		for(User user: users) {
			if(user.getPincode() == pincode) {
				logger.info("searching user is matched with given pincode {}", pincode);
				return user;
			}
		}
		return null;
	}
	
	public boolean deleteUserById(Long id) {
		logger.debug("deleting existing user with given ID {}", id);
		Optional<User> found = userRepository.findById(id);
		if(found.isPresent()) {
			User foundUser = found.get();
			userRepository.delete(foundUser);
			logger.info("existing user found and deleted");
			return true;
		}
		return false;
	}
	
	public List<User> sortUserByDobAndJoiningDate(String dob, String joiningDate) {
		logger.info("sorting user by {}", dob, " and {}", joiningDate);
		return (List<User>) userRepository.findAll(Sort.by(Direction.ASC, dob, joiningDate));
	}
}
