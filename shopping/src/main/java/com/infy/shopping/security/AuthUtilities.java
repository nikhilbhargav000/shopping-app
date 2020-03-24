package com.infy.shopping.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.repository.UserRepository;

@Component
public class AuthUtilities {
	
	private final UserRepository userRepository;
	
	@Autowired
	public AuthUtilities(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean isAuthenticatedUser(String userId, Authentication authentication) {
		String authEmail = authentication.getName();
		if (StringUtils.isBlank(authEmail))
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid authentication username");

		UserEntity authUserEntity = userRepository.findByEmail(authEmail);
		if (authUserEntity == null || StringUtils.isBlank(authUserEntity.getUserId()) || StringUtils.isBlank(userId)
				|| !userId.equals(authUserEntity.getUserId()))
			throw new SAppsException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
		return true;
	}
	
}
