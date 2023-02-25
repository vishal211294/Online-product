package com.iiht.training.auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.service.BidsService;
import com.iiht.training.auction.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BidsService bidsService;

	@PostMapping(value = "register" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDto register(@Valid @RequestBody CustomerDto customerDto) {
		return customerService.registerCustomer(customerDto);
	}
	
	@PutMapping(value = "update" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDto update(@Valid @RequestBody CustomerDto customerDto) {
		return customerService.updateCustomer(customerDto);
	}
	
	@GetMapping(value = "get/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDto> getAll() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping(value = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDto get(@PathVariable(name = "id") Long id) {
		return customerService.getCustomerById(id);
	}
	
	@DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean delete(@PathVariable(name = "id") Long id) {
		return customerService.deleteCustomer(id);
	}
	
	@PostMapping(value = "place-bid" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BidsDto placeBid(@Valid @RequestBody BidsDto bidsDto) {
		return bidsService.placeBid(bidsDto);
	}
	
	@GetMapping(value = "get/all-bids-on-product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BidsDto> getAllProductBid(@PathVariable(name = "id") Long id) {
		return bidsService.getAllBidsAfterProductBiddingEndDate(id);
	}
}
