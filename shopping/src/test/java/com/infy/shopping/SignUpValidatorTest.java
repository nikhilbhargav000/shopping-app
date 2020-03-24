package com.infy.shopping;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.user.SignUp;
import com.infy.shopping.validator.SignUpValidator;

public class SignUpValidatorTest {

	SignUpValidator signUpValidator;
	
	@Before
	public void initlialize() {
		signUpValidator = new SignUpValidator();
	}
	
	@Test
	public void testSignUp1() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							.password("Abcd#1234")
							.confirmPassword("Abcd#1234")
//							.accountType("REGESTERED")
							.build();
		
		assertTrue(signUpValidator.isValid(signUp));
	}

	@Test(expected=SAppsException.class)
	public void testSignUp2() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							// Password without specials char
							.password("Abcd1234") 
							.confirmPassword("Abcd1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}

	@Test(expected=SAppsException.class)
	public void testSignUp3() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							// Password without Capital letter
							.password("abcd#1234") 
							.confirmPassword("abcd#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}

	@Test(expected=SAppsException.class)
	public void testSignUp4() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							// Password without smaller case letter
							.password("ABCD#1234") 
							.confirmPassword("ABCD#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}


	@Test(expected=SAppsException.class)
	public void testSignUp5() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							// Password without digit 
							.password("abcd#ABCD") 
							.confirmPassword("abcd#ABCD")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}
	
	@Test(expected=SAppsException.class)
	public void testSignUp6() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@infosys.com")
							// confirm password and password not matching
							.password("Abcd#1234") 
							.confirmPassword("Xyz#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}
	

	@Test
	public void testSignUp7() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							// with valid email
							.email("nikhil.bhargav01@gmail.co.in")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertTrue(signUpValidator.isValid(signUp));
	}

	@Test(expected=SAppsException.class)
	public void testSignUp8() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							// with invalid email
							.email("nikhil.bhargav01@gmail.conn")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}
	
	@Test(expected=SAppsException.class)
	public void testSignUp9() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							// with invalid email
							.email("nikhil.bhargav01$gmail.com")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
//							.accountType("REGESTERED")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}
	

	@Test(expected=SAppsException.class)
	public void testSignUp10() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@gmail.com")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
							// with invalid accountType
//							.accountType("REGESTEREDaa")
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}

	@Test
	public void testSignUp11() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@gmail.com")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
//							.accountType("GUEST")
							.build();
		
		
		assertTrue(signUpValidator.isValid(signUp));
	}
	

//	@Test(expected=SAppsException.class)
//	public void testSignUp12() {
//		SignUp signUp = SignUp.builder()
//							.name("Nikhil Bhargav")	
//							.email("nikhil.bhargav01@gmail.com")
//							.password("Abcd#1234") 
//							.confirmPassword("Abcd#1234")
//							// with invalid accountType
//							.accountType("Guest")
//							.build();
//		
//		
//		assertFalse(signUpValidator.isValid(signUp));
//	}
	
	@Test(expected=SAppsException.class)
	public void testSignUp13() {
		SignUp signUp = SignUp.builder()
							.name("Nikhil Bhargav")	
							.email("nikhil.bhargav01@gmail.com")
							.password("Abcd#1234") 
							.confirmPassword("Abcd#1234")
							// Mandatory Field missing
							.build();
		
		
		assertFalse(signUpValidator.isValid(signUp));
	}
	
}
