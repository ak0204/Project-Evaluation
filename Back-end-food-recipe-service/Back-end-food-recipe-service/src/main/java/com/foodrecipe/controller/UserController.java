package com.foodrecipe.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.foodrecipe.entity.User;
import com.foodrecipe.entity.UserCred;
import com.foodrecipe.service.IUserDetailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( UserController.class );

	@Autowired
	private IUserDetailService userDetailService;

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getuserByName(@PathVariable("id") String name) {
		LOGGER.info("Starting of getuserByName(), username="+ name);
		User user = userDetailService.getUserByName(name);
		user.setRole(userDetailService.getRoleById(user.getRoleId()).getRoleName());
		LOGGER.info("Ending of getuserByName(), username="+ name);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/adduser")
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) {
		LOGGER.info("Starting of addUser(), username="+ user.getUserName());
		user.setRoleId(2);
		User exist = userDetailService.getUserByName(user.getUserName());
		if(Objects.isNull(exist)) {
		boolean flag = userDetailService.addUser(user);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		LOGGER.info("Ending of addUser(), username="+ user.getUserName());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			LOGGER.warn("User Already exists in the system, username="+ user.getUserName());
			throw new RuntimeException("User already exists");
		}
	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUserDetails(@RequestBody User user) {
		LOGGER.info("Starting of updateUserDetails(), username="+ user.getUserName());
		userDetailService.updateUser(user);
		//user.setRole(userDetailService.getRoleById(user.getRoleId()).getRoleName());
		LOGGER.info("Ending of updateUserDetails(), username="+ user.getUserName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/basicauth")
	public ResponseEntity<UserCred> basicAuth(@RequestBody UserCred cred) {
		LOGGER.info("Starting of basicAuth(), username="+ cred.getUsername());
		System.out.println("Basic Auth");
		User user = userDetailService.getUserByName(cred.getUsername());
		if(!user.getPassword().equalsIgnoreCase(cred.getPassword())) {
			LOGGER.warn("Password doesnot matched, username="+ cred.getUsername());
			throw new RuntimeException("Invalid Credentials");
		}
		cred.setRole(userDetailService.getRoleById(user.getRoleId()).getRoleName());
		LOGGER.info("Ending of basicAuth(), username="+ cred.getUsername());
		return new ResponseEntity<UserCred>(cred, HttpStatus.OK);

	}
	

}