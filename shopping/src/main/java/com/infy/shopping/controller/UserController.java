package com.infy.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.shopping.model.SAppResponseMessage;
import com.infy.shopping.model.WishlistItem;
import com.infy.shopping.model.WishlistResponse;
import com.infy.shopping.model.user.User;
import com.infy.shopping.service.SAppUserDetailsService;
import com.infy.shopping.service.WishlistService;

@RestController
@RequestMapping(value="/{userId}/")
public class UserController {

	private final WishlistService wishlistService;
	private final SAppUserDetailsService userService;
	
	@Autowired
	public UserController(WishlistService wishlistService, SAppUserDetailsService userService) {
		this.wishlistService = wishlistService;
		this.userService = userService;
	}
	
	@PostMapping(value="/update")
	public ResponseEntity<SAppResponseMessage> updateUser(@PathVariable("userId") String userId, @RequestBody User user,
			Authentication authentication) {
		SAppResponseMessage response = userService.updateUser(userId, user, authentication);
		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.OK);
	}
	
	@PostMapping(value="/wishlist/{productName}/add")
	@CacheEvict(value="wishlist", key="#userId")
	public ResponseEntity<SAppResponseMessage> addWishlistProduct(@PathVariable("userId") String userId,
			@PathVariable("productName") String productName, @RequestBody WishlistItem wishlistItem,
			Authentication authentication) {
		SAppResponseMessage response = wishlistService.addWishlistItem(userId, productName, wishlistItem,
				authentication);
		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.CREATED);
	}

	@GetMapping(value="/wishlist")
	@Cacheable(value="wishlist", key="#userId")
	public WishlistResponse getWishlist(@PathVariable("userId") String userId,
			Authentication authentication) {
		WishlistResponse response = wishlistService.getWishlist(userId, authentication);
		return response;
	}
	
	@PostMapping(value="/wishlist/{productName}/remove")
	@CacheEvict(value="wishlist", key="#userId")
	public ResponseEntity<SAppResponseMessage> removeWishlistProduct(@PathVariable("userId") String userId,
			@PathVariable("productName") String productName, Authentication authentication) {
		SAppResponseMessage response = wishlistService.removeWishlistItem(userId, productName, authentication);
		return new ResponseEntity<SAppResponseMessage>(response, HttpStatus.OK);
	}
	
}
