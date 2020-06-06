package com.infy.shopping.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class WishlistResponse extends SAppResponseMessage implements Serializable {

	private static final long serialVersionUID = -8652783780862813225L;

	private List<WishlistItem> wishlist;

}
