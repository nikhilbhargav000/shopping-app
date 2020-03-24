package com.infy.shopping.translator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.infy.shopping.entity.WishlistEntity;
import com.infy.shopping.entity.WishlistIdentityEntity;
import com.infy.shopping.model.WishlistItem;

@Component
public class WishlistTranslator {
	
	public WishlistEntity getWishlistEntity(String userId, String productName, WishlistItem wishlistItem) {
		WishlistIdentityEntity wishlistId = WishlistIdentityEntity.builder()
				.userId(userId)
				.productName(productName)
				.build();
		
		WishlistEntity wishlistEntity = WishlistEntity.builder()
				.wishlistId(wishlistId)
				.displayName(wishlistItem.getDisplayName())
				.shortDesc(wishlistItem.getShortDesc())
				.category(wishlistItem.getCategory())
				.build();
		return wishlistEntity;
	}
	
	public List<WishlistItem> getWishlistItems(List<WishlistEntity> wishlistEntities) {
		List<WishlistItem> wishlistItems = new ArrayList<>();
		
		for (WishlistEntity wishlistEntity : wishlistEntities) {
			wishlistItems.add(getWishlistItem(wishlistEntity));
		}
		
		return wishlistItems;
	}
	
	public WishlistItem getWishlistItem(WishlistEntity wishlistEntity) {
		WishlistItem wishlistItem = WishlistItem.builder()
				.displayName(wishlistEntity.getDisplayName())
				.shortDesc(wishlistEntity.getShortDesc())
				.category(wishlistEntity.getCategory())
				.build();
		
		return wishlistItem;
	}
	
}
