package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	//find a user by id
	User findById(int id);
	
	//query to fin a user by email
	@Query("SELECT u from User u WHERE u.email=:email")
	User findByEmail(String email);

}
