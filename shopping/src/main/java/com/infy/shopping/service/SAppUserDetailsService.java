package com.infy.shopping.service;

import org.springframework.security.core.Authentication;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.User;

public interface SAppUserDetailsService {

	public SAppResponseMessage updateUser(String userId, User user, Authentication authentication) ;
	
}
