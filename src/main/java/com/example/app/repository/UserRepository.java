package com.example.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

//	public void registerNewUser(User user);
//	public void editUserById(Long id);
//	public User searchUserByName(String name);
//	public User searchUserBySurname(String surname);
//	public User searchUserByPincode(int pincode);
//	public User sortUserByDobAndJoiningDate(LocalDate dob, LocalDate joiningDate);
	
}
