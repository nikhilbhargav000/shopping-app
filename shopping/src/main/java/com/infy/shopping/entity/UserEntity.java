package com.infy.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="users")
public class UserEntity {
	@Id
	@Column(name="user_id")
	private int userId;
	private String name;
	private String email;
	private String password;
	@Column(name="account_type")
	private String accountType;
	
}
