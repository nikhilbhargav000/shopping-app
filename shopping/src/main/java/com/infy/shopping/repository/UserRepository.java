package com.infy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.shopping.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	public UserEntity findByEmail(String email) ;
	
}
