package com.infy.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class WishlistIdentityEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="product_name")
	private String productName;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (!(obj instanceof WishlistIdentityEntity))	return false;
		WishlistIdentityEntity wishlistIdentityEntity = (WishlistIdentityEntity) obj;
		
		if (this.userId.equals(wishlistIdentityEntity.userId)
				&& this.productName.equals(wishlistIdentityEntity.productName)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return (this.userId.hashCode() * 31) + this.productName.hashCode();
	}
}
