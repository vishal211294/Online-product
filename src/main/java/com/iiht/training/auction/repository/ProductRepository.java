package com.iiht.training.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.auction.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findAllBySellerId(Long sellerId);
	
	List<ProductEntity> findAllByCategory(String category);
	
	
}
