package com.iiht.training.auction.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.entity.ProductEntity;
import com.iiht.training.auction.entity.SellerEntity;
import com.iiht.training.auction.exceptions.ProductNotFoundException;
import com.iiht.training.auction.exceptions.SellerNotFoundException;
import com.iiht.training.auction.repository.ProductRepository;
import com.iiht.training.auction.repository.SellerRepository;
import com.iiht.training.auction.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private EntityManager entityManager;

	public ProductDto saveProduct(ProductDto productDto) {
		getSellerDetail(productDto.getSellerId());
		return convertEntityToDto(productRepository.save(convertDtoToEntity(productDto)));
	}

	public ProductDto updateProduct(ProductDto productDto) {
		getProductDetail(productDto.getProductId());
		return convertEntityToDto(entityManager.merge(convertDtoToEntity(productDto)));
	}

	public Boolean deleteProduct(Long productId) {
		getProductDetail(productId);
		productRepository.deleteById(productId);
		return true;
	}

	public ProductDto getProductById(Long productId) {
		return convertEntityToDto(getProductDetail(productId));
	}

	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream().map(entity -> convertEntityToDto(entity)).toList();
	}

	public List<ProductDto> getProductsBySeller(Long sellerId) {
		return productRepository.findAllBySellerId(sellerId).stream().map(entity -> convertEntityToDto(entity)).toList();
	}

	public List<ProductDto> getProductsByCategory(String category) {
		return productRepository.findAllByCategory(category).stream().map(entity -> convertEntityToDto(entity)).toList();
	}
	
	private SellerEntity getSellerDetail(Long id) {
		return sellerRepository.findById(id).orElseThrow(()-> new SellerNotFoundException());
	}
	
	private ProductEntity getProductDetail(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
	}
}
