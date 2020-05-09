
package com.infy.shopping.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.user.AccountType;
import com.infy.shopping.model.user.SignUp;

@Component
public class SignUpValidator implements Validator<SignUp> {

	@Override
	public boolean isValid(SignUp signUp) throws SAppsException {

		if (isMadatoryFieldsPresent(signUp) && isValidName(signUp.getName())
				&& isValidConfirmPassword(signUp.getConfirmPassword(), signUp.getPassword())
				&& isValidPassword(signUp.getPassword()) && isValidEmail(signUp.getEmail())) {
			return true;
		}
		return false;
	}

	
	public boolean isValidName(String name) throws SAppsException {
		String regex = "[a-zA-Z ]+";
		if (StringUtils.isBlank(name) || !Pattern.matches(regex, name)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Name contains invalid characters");
		}
		return true;
	}
	
	public boolean isValidPassword(String password) throws SAppsException {
		String regex = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{4,}";
		if (!Pattern.matches(regex, password)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(),
					"Password should contain atleast a uppercase and a lower case , a number and a special character");
		}
		return true;
	}
	
	public boolean isValidConfirmPassword(String password, String confirmPassword) throws SAppsException {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword) || 
				!password.equals(confirmPassword)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(),
					"Password and confirm password should be same");
		}
		return true;
	}
	
	public boolean isValidEmail(String email) throws SAppsException {

		EmailValidator emailValidator = EmailValidator.getInstance();
		if (!emailValidator.isValid(email)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Email should be valid");
		}
		return true;
	}
	
	public boolean isValidAccountType(String accountType) throws SAppsException {
		
		if (StringUtils.isBlank(accountType)) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Account type cannot be empty");
		}
		try {
			AccountType.valueOf(accountType);
		} catch (IllegalArgumentException e) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Account type should be valid");
		}
		return true;
	}
	
	public boolean isMadatoryFieldsPresent(SignUp signUp) throws SAppsException {
		if (StringUtils.isBlank(signUp.getName()) || StringUtils.isBlank(signUp.getEmail())
				|| StringUtils.isBlank(signUp.getPassword()) || StringUtils.isBlank(signUp.getConfirmPassword())) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "All fields are mandatory");
		}
		return true;
	}

}
