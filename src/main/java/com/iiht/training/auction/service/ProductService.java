package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.entity.ProductEntity;

public interface ProductService {
	public ProductDto saveProduct(ProductDto productDto);

	public ProductDto updateProduct(ProductDto productDto);

	public Boolean deleteProduct(Long productId);

	public ProductDto getProductById(Long productId);

	public List<ProductDto> getAllProducts();

	public List<ProductDto> getProductsBySeller(Long sellerId);
	public List<ProductDto> getProductsByCategory(String category);

	
	default ProductEntity convertDtoToEntity(ProductDto productDto) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCategory(productDto.getCategory());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setLastDateOfBidding(productDto.getLastDateOfBidding());
		productEntity.setName(productDto.getName());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setProductId(productDto.getProductId());
		productEntity.setQuantity(productDto.getQuantity());
		productEntity.setSellerId(productDto.getSellerId());
		productEntity.setStartingBidAmount(productDto.getStartingBidAmount());
		return productEntity;
	} 
	
	default ProductDto convertEntityToDto(ProductEntity productDto) {
		ProductDto productEntity = new ProductDto();
		productEntity.setCategory(productDto.getCategory());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setLastDateOfBidding(productDto.getLastDateOfBidding());
		productEntity.setName(productDto.getName());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setProductId(productDto.getProductId());
		productEntity.setQuantity(productDto.getQuantity());
		productEntity.setSellerId(productDto.getSellerId());
		productEntity.setStartingBidAmount(productDto.getStartingBidAmount());
		return productEntity;
	} 
}
