package com.infy.shopping.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@ToString
@Builder
public class User {
	
	private String name;
	private String password;
	
}
