package com.infy.shopping.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class Login {
	
	private String userEmail;
	private String password;
	private String guestId;
	private boolean createGuestUser;
	
}
