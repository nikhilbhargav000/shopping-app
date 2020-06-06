package com.infy.shopping.model;

import java.io.Serializable;

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
public class WishlistItem implements Serializable {

	private static final long serialVersionUID = -3465241290814279032L;
	
	private String displayName;
	private String shortDesc;
	private String category;
	
}
