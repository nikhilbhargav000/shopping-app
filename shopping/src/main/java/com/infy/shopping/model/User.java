package com.infy.shopping.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
@ToString
public class User {
	
	private UUID userId;
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private String accountType;
	
}
