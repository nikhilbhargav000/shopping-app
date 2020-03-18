package com.infy.shopping.validator;

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

	public boolean isValidPassword(String password) {
		return true;
	}
	
	public boolean isValidEmail(String email) {
		
		return true;
	}
	
	public boolean isMadatoryFieldsPresent(SignUp signUp) {
		if (StringUtils.isBlank(signUp.getName()) || StringUtils.isBlank(signUp.getEmail()) || 
				StringUtils.isBlank(signUp.getPassword()) || StringUtils.isBlank(signUp.getConfirmPassword()) || 
				StringUtils.isBlank(signUp.getAccountType())) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Mandatory fields cannot be empty");
		}
		return true;
	}
	
}
