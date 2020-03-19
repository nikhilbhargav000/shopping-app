package com.infy.shopping.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.SignUp;

@Component
public class SignUpValidator implements Validator<SignUp> {

	@Override
	public boolean valid(SignUp signUp) {
		
		return true;
	}

	
	public boolean isValidName(String name) {
		String regex = "[a-zA-Z]+";
		if (!Pattern.matches(regex, name)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Name contains invalid characters");
		}
		return true;
	}
	
	public boolean isValidPassword(String password) {
		String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])";
		if (!Pattern.matches(regex, password)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(),
					"Password should contain atleast a uppercase and a lower case , a number and a special character");
		}
		return true;
	}
	
	public boolean isValidEmail(String email) {
		
		return true;
	}
	
	public boolean isMadatoryFieldsPresent(SignUp signUp) {
		if (StringUtils.isBlank(signUp.getName()) || StringUtils.isBlank(signUp.getEmail()) || 
				StringUtils.isBlank(signUp.getPassword()) || StringUtils.isBlank(signUp.getConfirmPassword()) || 
				StringUtils.isBlank(signUp.getAccountType())) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "All fields are mandatory");
		}
		return true;
	}
	
}
