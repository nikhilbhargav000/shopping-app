package com.infy.shopping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.SignUp;
import com.infy.shopping.validator.SignUpValidator;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	SignUpValidator signUpValidator;

	@Override
	public SAppResponseMessage signUpUser(SignUp signUp) {

		signUpValidator.isValid(signUp);

		SAppResponseMessage response = new SAppResponseMessage();

		UUID uuId = UUID.randomUUID();
		System.out.println(uuId.toString());

		return response;
	}

}
