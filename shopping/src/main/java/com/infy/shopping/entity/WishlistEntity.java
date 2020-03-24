package com.infy.shopping.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="wish_lists")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class WishlistEntity {
	
	@EmbeddedId
	private WishlistIdentityEntity wishlistId;

	@Column(name="display_name")
	private String displayName;

	@Column(name="short_desc")
	private String shortDesc;
	
	private String category;
	
}
