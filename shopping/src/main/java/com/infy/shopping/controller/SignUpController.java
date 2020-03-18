package com.infy.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.shopping.model.SignUp;
import com.infy.shopping.service.SignUpService;

@RestController(value="signup")
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;
	
	@PostMapping()
	public ResponseEntity signupUser (@RequestBody SignUp signUp) {
		signUpService.signUpUser(signUp);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
