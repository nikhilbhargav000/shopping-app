package com.infy.shopping.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class SignUp {
	
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private String guestId;
	
}
