package com.infy.shopping.model.user;

import com.infy.shopping.model.SAppResponseMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class LoginResponse extends SAppResponseMessage {

	private String userId;
	private String jwtToken;
	
	public LoginResponse(String userId, String jwtToken) {
		this.userId = userId;
		this.jwtToken = jwtToken;
	}
	
	
}
