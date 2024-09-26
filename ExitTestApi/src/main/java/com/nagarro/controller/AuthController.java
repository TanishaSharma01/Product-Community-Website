package com.nagarro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.User;
import com.nagarro.exception.ApiRequestException;
import com.nagarro.model.LoginModel;
import com.nagarro.model.ResponseJson;
import com.nagarro.service.UserService;
import com.nagarro.utils.Constants;
import com.nagarro.utils.CookieParser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = Constants.CLIENT_URL, allowCredentials = Constants.TRUE)
@RequestMapping(Constants.API_V1) //api versioning by including version no. in uri path
public class AuthController {
	@Autowired
	UserService userService;
	
	//Authenticating to log in a user
	@PostMapping("/users/login")
	public ResponseEntity<Object> loginUser(@RequestBody LoginModel loginBody, HttpServletResponse response) {
		try {

		//Both email password is null
		if ((loginBody.getEmail() == null || loginBody.getEmail() == "") && (loginBody.getPassword() == null || loginBody.getPassword() == "")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseJson("Please enter email password"));
		}
		
		//User for email doesn't exist
		User userFromDatabase = this.userService.getUserByEmail(loginBody.getEmail());
		if (userFromDatabase == null) { // user doesn't exist
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseJson("User doesn't exist"));
		}
		//Password is null
		else if (loginBody.getPassword() == null || loginBody.getPassword() == "") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseJson("Please enter password"));
		} 
		//Password doesn't match the password in database
		else if (loginBody.getPassword().equals(userFromDatabase.getPassword()) == false) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseJson("Invalid email password"));
		}

		//Set Cookies
		String cookieValue = CookieParser.createCookieValue(userFromDatabase.getId(), userFromDatabase.getEmail());
		Cookie cookie = new Cookie("user", cookieValue);
		cookie.setPath("/"); // Set the cookie path to root, global cookie accessible everywhere
		cookie.setMaxAge(60*60); // Set the cookie expiration time for 1 hour
		cookie.setSecure(true); // Set the cookie to secure cookie, sent to the server over an encrypted HTTPS connection
		cookie.setHttpOnly(true); //Used to prevent cross-site scripting (XSS) attacks and are not accessible via JavaScript's Document.cookie API
		response.addCookie(cookie); //add cookie to response
		
		//Create a hashmap to send in response objects
		Map<String, Object> response1 = new HashMap<>();
		
		response1.put("auth",true); //authorized true
		response1.put("firstName",userFromDatabase.getFirstName()); //first name
		response1.put("lastName",userFromDatabase.getLastName()); //last name
		response1.put("email",userFromDatabase.getEmail()); //email
		
		//user login successful
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseJson("User Logged in successfully", response1)); 
		}
		catch(Exception e) {
			//user login unsuccessful
			throw new ApiRequestException("User login unsuccessful.");
		}
	}
	
	//logout a user
	@PostMapping("/users/logout")
	public ResponseEntity<Object> logoutUser(HttpServletRequest request, HttpServletResponse response){
		try {
			
		//get cookies
		Cookie[] cookies = request.getCookies();
		
		//if cookies not null, expire them
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        //Logout success
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson("Log-out Success"));
		}
		catch(Exception e) {
			//Logout unsuccessful
			throw new ApiRequestException("User logout unsuccessful.");
		}
	}
}

