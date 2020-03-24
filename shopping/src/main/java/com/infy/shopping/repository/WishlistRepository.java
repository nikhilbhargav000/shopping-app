package com.infy.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.shopping.entity.WishlistEntity;
import com.infy.shopping.entity.WishlistIdentityEntity;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, WishlistIdentityEntity> {
	
	public List<WishlistEntity> findByWishlistId_UserId(String userId) ;
	
	public WishlistEntity findByWishlistId_UserIdAndWishlistId_ProductName(String userId, String productName);
	
}
