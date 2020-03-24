package com.infy.shopping.service;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.user.Login;

public interface LoginService {

	public SAppResponseMessage login(Login login) ;
	
}
