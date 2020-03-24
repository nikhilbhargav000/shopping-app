package com.infy.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.Login;
import com.infy.shopping.service.LoginService;

@RestController
@RequestMapping("/login/")
public class LoginController {

	@Autowired
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<SAppResponseMessage> login(@RequestBody Login login) {
		SAppResponseMessage response = loginService.login(login);
		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.OK);
	}
}
