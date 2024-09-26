package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.UserDao;
import com.nagarro.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	//Save user
	public User saveUser(User userObj) {
		return this.userDao.save(userObj);
	}

	//get user by id
	public User getUserById(int id) {
		User userObj = new User();
		try {
			userObj = this.userDao.findById(id);
		} catch (Exception e) {
			new Exception("User not found");
		}
		return userObj;
	}

	//get list of all users
	public List<User> getAllUsers() {
		return this.userDao.findAll();
	}
	
	//check if user exists by email
	public Boolean checkUserExists(User user) {
		return (this.getUserByEmail(user.getEmail()) != null);
	}
	
	//get user by email
	public User getUserByEmail(String email) {
		return this.userDao.findByEmail(email);
	}
	
	//get Count of Users
	public Long getCountUser()
	{
		return this.userDao.count();
	}
}
