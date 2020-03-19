package com.infy.shopping.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.infy.shopping.model.SignUp;


@WebMvcTest(value=SignUpController.class)
public class SignUpControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void fetchSignUpTest1 () {
		
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")
							.email("nikhil.bhargav01")
							.password("Abc#1234")
							.confirmPassword("Abc#1234")
							.accountType("GUEST")
							.build();
		
		
		
	}
	
}
