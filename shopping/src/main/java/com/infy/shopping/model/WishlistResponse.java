package com.infy.shopping.model;

import java.util.List;

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
public class WishlistResponse extends SAppResponseMessage{

	private List<WishlistItem> wishlist;
	
}
