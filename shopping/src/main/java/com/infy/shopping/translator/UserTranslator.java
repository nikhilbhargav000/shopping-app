package com.infy.shopping.translator;

import org.springframework.stereotype.Component;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.model.user.SAppUser;
import com.infy.shopping.model.user.SignUp;

@Component
public class UserTranslator {
	
	public UserEntity getUserEntity(SignUp signUp) {
		UserEntity userEntity = UserEntity.builder()
				.name(signUp.getName())
				.email(signUp.getEmail())
				.password(signUp.getPassword())
				.build();
		
		return userEntity;
	}
	
	public SAppUser getUser(UserEntity userEntity) {
		SAppUser user = SAppUser.builder()
				.userId(userEntity.getUserId())
				.name(userEntity.getName())
				.email(userEntity.getEmail())
				.password(userEntity.getPassword())
				.accountType(userEntity.getAccountType())
				.build();
		
		return user;
	}
}
