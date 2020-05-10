package com.infy.shopping.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.AccountType;
import com.infy.shopping.model.user.Login;
import com.infy.shopping.model.user.LoginResponse;
import com.infy.shopping.repository.UserRepository;
import com.infy.shopping.security.jwt.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	
	public LoginServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public SAppResponseMessage login(Login login) {
		
		SAppResponseMessage response = new SAppResponseMessage();
		
		if (StringUtils.isBlank(login.getUserEmail()) && StringUtils.isBlank(login.getPassword()) && 
				StringUtils.isBlank(login.getGuestId()) && !login.isCreateGuestUser()) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid login request");
		
		if (isUserLoginRequest(login)) {
			response = loginUser(login);
		} else if (isGuestLoginRequest(login)) {
			response = loginGuestUser(login);
		} else if (isCreateGuestLoginRequest(login)) {
			response = createGuestUser(login);
		}
		
		return response;
	}
	
	public SAppResponseMessage loginUser(Login login) {
		
		LoginResponse response = new LoginResponse();
		
		UserEntity userEntity = userRepository.findByEmail(login.getUserEmail());
		if (userEntity == null || !login.getPassword().equals(userEntity.getPassword()))
			throw new SAppsException(HttpStatus.UNAUTHORIZED.value(), "Bad credencials");
		
		// Jwt token
		String jwtToken = jwtUtil.generateJwtToken(userEntity.getEmail());
		
		response.setMessage("User login successful");
		response.setUserId(userEntity.getUserId());
		response.setJwtToken(jwtToken);
		return response;
	}
	
	public SAppResponseMessage loginGuestUser(Login login) {
		SAppResponseMessage response = new SAppResponseMessage();
		
		UserEntity userEntity = userRepository.findById(login.getGuestId()).orElse(null);
		if (userEntity == null || !AccountType.GUEST.toString().equals(userEntity.getAccountType()))
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid guest id");
		
		response.setMessage("Guest login successful");
		response.setValue(userEntity.getUserId());
		return response;
	}
	
	public SAppResponseMessage createGuestUser(Login login) {
		SAppResponseMessage response = new SAppResponseMessage();
		
		UserEntity userEntity = UserEntity.builder()
				.userId(UUID.randomUUID().toString())
				.accountType(AccountType.GUEST.toString())
				.build();
		userRepository.saveAndFlush(userEntity);
		
		response.setMessage("Guest user created successfully");
		response.setValue(userEntity.getUserId());
		return response;
	}
	
	///////////////////////////////////////////////////////////////////
	/////// Privates
	///////////////////////////////////////////////////////////////////

	private boolean isUserLoginRequest(Login login) {
		if (StringUtils.isNotBlank(login.getUserEmail()) && StringUtils.isNotBlank(login.getPassword())) {
			return true;
		}
		return false;
	}
	
	private boolean isGuestLoginRequest(Login login) {
		if (StringUtils.isBlank(login.getUserEmail()) && StringUtils.isBlank(login.getPassword()) 
				&& StringUtils.isNotBlank(login.getGuestId())) {
			return true;
		}
		return false;
	}
	
	private boolean isCreateGuestLoginRequest(Login login) {
		if (StringUtils.isBlank(login.getUserEmail()) && StringUtils.isBlank(login.getPassword()) 
				&& StringUtils.isBlank(login.getGuestId()) && login.isCreateGuestUser()) {
			return true;
		}
		return false;
	}
	
}
