package com.infy.shopping.service;

import org.springframework.security.core.Authentication;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.WishlistItem;
import com.infy.shopping.model.WishlistResponse;

public interface WishlistService {
	
	public SAppResponseMessage addWishlistItem(String userId, String productName, WishlistItem wishlistItem, Authentication authentication) ;

	public WishlistResponse getWishlist(String userId, Authentication authentication) ;
	
	public SAppResponseMessage removeWishlistItem(String userId, String productName, Authentication authentication) ;
	
}
