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

import com.iiht.training.auction.dto.BidsDto;
import com.iiht.training.auction.dto.ProductDto;
import com.iiht.training.auction.dto.SellerDto;
import com.iiht.training.auction.service.BidsService;
import com.iiht.training.auction.service.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private BidsService bidsService;

	
	@PostMapping(value = "register" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SellerDto register(@Valid @RequestBody SellerDto productDto) {
		return sellerService.registerSeller(productDto);
	}
	
	@PutMapping(value = "update" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SellerDto update(@Valid @RequestBody SellerDto seller) {
		return sellerService.updateSeller(seller);
	}
	
	@GetMapping(value = "get/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SellerDto> getAll() {
		return sellerService.getAllSellers();
	}
	
	@GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SellerDto get(@PathVariable(name = "id") Long id) {
		return sellerService.getSellerById(id);
	}
	
	@DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean delete(@PathVariable(name = "id") Long id) {
		return sellerService.deleteSeller(id);
	}
	
	@GetMapping(value = "get/bids-on-product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BidsDto> getProductBids(@PathVariable(name = "id") Long id) {
		return bidsService.getAllBidsOnProduct(id);
	}
}
