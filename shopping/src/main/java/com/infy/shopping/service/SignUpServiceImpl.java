package com.infy.shopping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.shopping.model.SignUp;
import com.infy.shopping.validator.SignUpValidator;
import com.infy.shopping.validator.Validator;

@Service
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	SignUpValidator signUpValidator;
	
	
	public void signUpUser (SignUp signUp) {
		
		signUpValidator.isValid(signUp);
		
		UUID uuId = UUID.randomUUID();
//		UUID uuId = new UUI
		System.out.println(uuId.toString());
		
		
	}
	
}
