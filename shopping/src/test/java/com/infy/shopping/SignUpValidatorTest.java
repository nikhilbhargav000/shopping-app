package com.infy.shopping;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.shopping.model.SignUp;
import com.infy.shopping.validator.SignUpValidator;

//@RunWith(SpringRunner.class)
public class SignUpValidatorTest {

	SignUpValidator signUpValidator;
	
	@Before
	public void initlialize() {
		signUpValidator = new SignUpValidator();
	}
	
	@Test
	public void testSignUp1() {
//		signUpValidator = new SignUpValidator();
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							.password("Abcd#1234")
							.confirmPassword("Abcd#1234")
							.accountType("REGESTERED")
							.build();
		
		signUpValidator.isValid(signUp);
		
//		assertTrue();
	}
}
