package com.infy.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.shopping.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
	
}
