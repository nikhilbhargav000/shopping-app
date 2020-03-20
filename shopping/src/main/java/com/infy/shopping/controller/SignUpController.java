package com.infy.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.SignUp;
import com.infy.shopping.service.SignUpService;

@RestController
@RequestMapping(value="signup")
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;
	
	@PostMapping()
	public ResponseEntity signupUser (@RequestBody SignUp signUp) {
		SAppResponseMessage response = signUpService.signUpUser(signUp); 
		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.CREATED);
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<SAppResponseMessage> signupUserTest (SignUp signUp) {
//		SAppResponseMessage response = signUpService.signUpUser(signUp); 
//		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.CREATED);
//	}
	
}
