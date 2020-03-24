package com.infy.shopping.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.infy.shopping.entity.UserEntity;
import com.infy.shopping.entity.WishlistEntity;
import com.infy.shopping.exception.SAppsException;
import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.WishlistItem;
import com.infy.shopping.model.WishlistResponse;
import com.infy.shopping.model.user.AccountType;
import com.infy.shopping.repository.ProductRepository;
import com.infy.shopping.repository.UserRepository;
import com.infy.shopping.repository.WishlistRepository;
import com.infy.shopping.security.AuthUtilities;
import com.infy.shopping.translator.WishlistTranslator;

@Service
public class WishlistServiceImpl implements WishlistService {
	
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final WishlistRepository wishlistRepository;
	private final WishlistTranslator wishlistTranslator;
	private final AuthUtilities authUtilities;
	
	@Autowired
	public WishlistServiceImpl(UserRepository userRepository, ProductRepository productRepository,
			WishlistRepository wishlistRepository, WishlistTranslator wishlistTranslator, AuthUtilities authUtilities) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.wishlistRepository = wishlistRepository;
		this.wishlistTranslator = wishlistTranslator;
		this.authUtilities = authUtilities;
	}
	
	@Override
	public SAppResponseMessage addWishlistItem(String userId, String productName, WishlistItem wishlistItem,
			Authentication authentication) {
		SAppResponseMessage response = new SAppResponseMessage();
		
		isValidUserIdAndProductName(userId, productName, authentication);
		
		WishlistEntity wishlistEntity = wishlistRepository.findByWishlistId_UserIdAndWishlistId_ProductName(userId, productName);
		if (wishlistEntity != null) 
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Product already saved in wishlist");
		
		wishlistEntity = wishlistTranslator.getWishlistEntity(userId, productName, wishlistItem);
		wishlistRepository.saveAndFlush(wishlistEntity);
		
		response.setMessage("Wishlist added successfully");
		return response;
	}
	
	@Override
	public WishlistResponse getWishlist(String userId, Authentication authentication) {
		WishlistResponse response = new WishlistResponse();
		
		authUtilities.isAuthenticatedUser(userId, authentication);
		isValidUserId(userId);
		
		List<WishlistEntity> wishlistEntities = wishlistRepository.findByWishlistId_UserId(userId);
		if (CollectionUtils.isEmpty(wishlistEntities)) 
			throw new SAppsException(HttpStatus.OK.value(), "No products found in wishlist");
		
		
		List<WishlistItem> wishlistItems = wishlistTranslator.getWishlistItems(wishlistEntities);
		response.setWishlist(wishlistItems);
		response.setMessage("Wishlist for user fetched successfully");
		
		return response;
	}
	
	@Override
	public SAppResponseMessage removeWishlistItem(String userId, String productName, Authentication authentication) {
	
		SAppResponseMessage response = new SAppResponseMessage();

		isValidUserIdAndProductName(userId, productName, authentication);
		
		WishlistEntity wishlistEntity = wishlistRepository.findByWishlistId_UserIdAndWishlistId_ProductName(userId, productName);
		if (wishlistEntity == null) 
			throw new SAppsException(HttpStatus.NOT_FOUND.value(), "Invalid user id and product name");
		wishlistRepository.delete(wishlistEntity);
		
		response.setMessage("Removed from wishlist successfully");
		return response;
	}
	
	
	///////////////////////////////////////////////////////////////////
	/////// Privates
	///////////////////////////////////////////////////////////////////
	
	private boolean isValidUserIdAndProductName(String userId, String productName, Authentication authentication) {

		return authUtilities.isAuthenticatedUser(userId, authentication) && isValidUserId(userId)
				&& isValidProductName(productName);
	}
	
	private boolean isValidUserId(String userId) {
		if (StringUtils.isBlank(userId))
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid user id");
		UserEntity user = userRepository.findById(userId).get();
		if (user == null)
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid user id");
		
		if (!user.getAccountType().equals(AccountType.REGESTERED.toString())) 
			throw new SAppsException(HttpStatus.PRECONDITION_FAILED.value(), "Only register can avail this service");
		return true;
	}
	
	private boolean isValidProductName(String productName) {
		if (StringUtils.isBlank(productName) || !productRepository.findById(productName).isPresent()) {
			throw new SAppsException(HttpStatus.BAD_REQUEST.value(), "Invalid product name");
		}
		return true;
	}

}
