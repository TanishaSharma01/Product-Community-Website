package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.User;
import com.nagarro.exception.ApiRequestException;
import com.nagarro.model.ResponseJson;
import com.nagarro.service.UserService;
import com.nagarro.utils.Constants;

@RestController
@CrossOrigin(origins=Constants.CLIENT_URL, allowCredentials = Constants.TRUE)
@RequestMapping(Constants.API_V1)
public class UserController {

	@Autowired
    private UserService userService;
	
	//to get all users
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers() {
		try {

		List<User> users = this.userService.getAllUsers();

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson("All users fetched",users));
		}
		catch(Exception e) {
			throw new ApiRequestException("Users could not be fetched.");
		}
	}

	//to register a user
	@PostMapping("/users/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
		try {
		
		//if any detail is null, user would not get added
		if(user.getEmail()==null || user.getFirstName()==null || user.getLastName()==null || user.getPassword()==null || 
				user.getEmail()=="" || user.getFirstName()=="" || user.getLastName()=="" || user.getPassword()=="") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson("User not added because a parameter left empty."));
		}
		
		//if user already exists a new user would not get added
		if( this.userService.checkUserExists(user) ) {
			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseJson("User already exists"));
		}
		
		//if no role provided by default the role is set as "user"
		if (user.getRole() != null && user.getRole().equalsIgnoreCase(Constants.ADMIN_ROLE)) {
			user.setRole(Constants.ADMIN_ROLE);
		} else {
			user.setRole(Constants.USER_ROLE);
		}
		
		//Save user
		User savedUser = this.userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseJson("User registered successfully", savedUser));
		}
		catch(Exception e) {
			throw new ApiRequestException("User registration unsuccessful.");
		}
		
	}

	//to count number of users
	@GetMapping("/users/count-users")
	public Long countUser() {
		try {
		Long countUser= this.userService.getCountUser();
		return countUser;			
		}
		catch(Exception e) {
			throw new ApiRequestException("User count could not be fetched.");
		}
	}
	
	//to get user by email
	@GetMapping("/users/{email}")
	public ResponseEntity<Object> getUserByEmail(@PathVariable String email)
	{
		try {
		User getUser=this.userService.getUserByEmail(email);
		//If user for this email doesn't exist
		if(getUser==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson("User for this email doesn't exist"));
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseJson("User found succesfully!", getUser));
		}
		catch(Exception e) {
			throw new ApiRequestException("User find unsuccessful.");
		}
	}
}
