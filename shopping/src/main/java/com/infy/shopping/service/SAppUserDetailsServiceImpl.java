package com.infy.shopping.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.SAppUser;
import com.infy.shopping.model.user.User;
import com.infy.shopping.repository.UserRepository;
import com.infy.shopping.security.AuthUtilities;
import com.infy.shopping.translator.UserTranslator;
import com.infy.shopping.validator.SignUpValidator;

@Service
public class SAppUserDetailsServiceImpl implements UserDetailsService, SAppUserDetailsService {
	
	private final UserRepository userRepository;
	private final UserTranslator userTranslator;
	private final SignUpValidator signUpValidator;
	private final AuthUtilities authUtilities;
	
	public SAppUserDetailsServiceImpl(UserRepository userRepository, UserTranslator userTranslator,
			SignUpValidator signUpValidator, AuthUtilities authUtilities) {
		this.userRepository = userRepository;
		this.userTranslator = userTranslator;
		this.signUpValidator = signUpValidator;
		this.authUtilities = authUtilities;
	}
	
	@Override
	public SAppResponseMessage updateUser(String userId, User user, Authentication authentication) {
		SAppResponseMessage response = new SAppResponseMessage();
		
		isValidUpdateUserRequest(userId, user);
		authUtilities.isAuthenticatedUser(userId, authentication);
		
		UserEntity userEntity = userRepository.findById(userId).orElse(null);
		if (userEntity == null)
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid user id");

		if (StringUtils.isNotBlank(user.getName()))	userEntity.setName(user.getName());
		if (StringUtils.isNotBlank(user.getPassword()))	userEntity.setPassword(user.getPassword());
		userRepository.saveAndFlush(userEntity);
		
		response.setMessage("Updated details successfully");
		return response;
	}
	
	public SAppUser getUser(String userId) throws SAppsException {
		if (StringUtils.isBlank(userId)) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid username");
		UserEntity userEntity = userRepository.findById(userId).get();
		if (userEntity == null) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid username");

		return userTranslator.getUser(userEntity);
	}
	
	public SAppUser getUserByEmail(String email) throws SAppsException {
		if (StringUtils.isBlank(email)) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid email");
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid email");

		return userTranslator.getUser(userEntity);
	}
	
	/**
	 * For Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SAppUser user = null;
		try {
			user = getUserByEmail(username);
		} catch (SAppsException e) {
			throw new UsernameNotFoundException("Invalid username");
		}
		return user;
	}
	
	///////////////////////////////////////////////////////////////////
	/////// Privates
	///////////////////////////////////////////////////////////////////

	public boolean isValidUpdateUserRequest(String userId, User user) {
		if (StringUtils.isBlank(userId)) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid user id");
		if (StringUtils.isBlank(user.getName()) && StringUtils.isBlank(user.getPassword()))
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid user id & password");
		
		if (StringUtils.isNotBlank(user.getName()) && !signUpValidator.isValidName(user.getName()))
			return false;
		if (StringUtils.isNotBlank(user.getPassword()) && !signUpValidator.isValidPassword(user.getPassword()))
			return false;
		
		return true;
	}
}
