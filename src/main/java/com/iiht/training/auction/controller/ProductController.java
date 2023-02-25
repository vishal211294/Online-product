package com.iiht.training.auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@PostMapping(value = "register" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto register(@Valid @RequestBody ProductDto productDto) {
		return productService.saveProduct(productDto);
	}
	
	@PutMapping(value = "update" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto update(@Valid @RequestBody ProductDto productDto) {
		return productService.updateProduct(productDto);
	}
	
	@GetMapping(value = "get/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAll() {
		return productService.getAllProducts();
	}
	
	@GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto get(@PathVariable(name = "id") Long id) {
		return productService.getProductById(id);
	}
	
	@DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean delete(@PathVariable(name = "id") Long id) {
		return productService.deleteProduct(id);
	}
	
	@GetMapping(value = "get/by-seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getBySeller(@PathVariable(name = "id") Long id) {
		return productService.getProductsBySeller(id);
	}
	
	@GetMapping(value = "get/by-category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> get(@PathVariable(name = "id") String id) {
		return productService.getProductsByCategory(id);
	}

}
