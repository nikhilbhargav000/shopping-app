package com.infy.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class WishlistItem {

	private String displayName;
	private String shortDesc;
	private String category;
	
}
