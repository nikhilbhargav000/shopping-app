package com.infy.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="products")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class ProductEntity {

	@Id
	@Column(name="product_name")
	private String productName;

	@Column(name="display_name")
	private String displayName;
	
	@Column(name="short_desc")
	private String shortDesc;
	
	private String description;
	
	private String category;
	
	private double price;

	private double discount;
	
	@Column(name="delivery_charges")
	private double deliveryCharges;
	
}
