package com.infy.shopping.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.AccountType;
import com.infy.shopping.model.user.SignUp;
import com.infy.shopping.repository.UserRepository;
import com.infy.shopping.translator.UserTranslator;
import com.infy.shopping.validator.SignUpValidator;

@Service
public class SignUpServiceImpl implements SignUpService {

	private final SignUpValidator signUpValidator;
	private final UserTranslator userTranslator;
	private final UserRepository userReporsitory;
	
	@Autowired
	public SignUpServiceImpl (SignUpValidator signUpValidator, UserTranslator userTranslator, 
			UserRepository userReporsitory) {
		this.signUpValidator = signUpValidator;
		this.userTranslator = userTranslator;
		this.userReporsitory = userReporsitory;
	}
	
	@Override
	public SAppResponseMessage signUpUser(SignUp signUp) {
		
		SAppResponseMessage response = new SAppResponseMessage();
		
		signUpValidator.isValid(signUp);
		if (userReporsitory.findByEmail(signUp.getEmail()) != null) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Email id is already used");
		}
		
		UserEntity userEntity = null;
		if (!isGuestSignupRequest(signUp)) {
			userEntity = signUpNewUser(signUp);
		} else {
			userEntity = signUpWithGuestId(signUp);
		}
		
		response.setValue(userEntity.getUserId());
		response.setMessage("Signup successful");
		return response;
	}

	private UserEntity signUpWithGuestId(SignUp signUp) {
		
		UserEntity userEntity = userReporsitory.findById(signUp.getGuestId()).orElse(null);
		if (userEntity == null || !AccountType.GUEST.toString().equals(userEntity.getAccountType())) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid guest id");
		userEntity.setName(signUp.getName());
		userEntity.setEmail(signUp.getEmail());
		userEntity.setPassword(signUp.getPassword());
		userEntity.setAccountType(AccountType.REGESTERED.toString());
		
		userReporsitory.saveAndFlush(userEntity);
		
		return userEntity;
	}
	
	private  UserEntity signUpNewUser(SignUp signUp) {
		UserEntity userEntity = userTranslator.getUserEntity(signUp);
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity.setAccountType(AccountType.REGESTERED.toString());
		userReporsitory.saveAndFlush(userEntity);
		
		return userEntity;
	}
	
	private boolean isGuestSignupRequest(SignUp signUp) {
		return StringUtils.isNotBlank(signUp.getGuestId());
	}
}
