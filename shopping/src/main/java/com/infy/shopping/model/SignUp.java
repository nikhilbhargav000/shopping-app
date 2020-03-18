package com.infy.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SignUp {
	
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private String accountType;
	
}
