package com.infy.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.infy.shopping.model.user.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="users")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class UserEntity {
	@Id
	@Column(name="user_id")
	private String userId;
	private String name;
	private String email;
	private String password;
	@Column(name="account_type")
	private String accountType;
	
}
