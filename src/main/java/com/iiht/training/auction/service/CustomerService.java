package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.entity.CustomerEntity;

public interface CustomerService {
	public CustomerDto registerCustomer(CustomerDto customerDto);

	public CustomerDto updateCustomer(CustomerDto customerDto);

	public Boolean deleteCustomer(Long id);

	public CustomerDto getCustomerById(Long id);

	public List<CustomerDto> getAllCustomers();
	
	default CustomerEntity convertDTOToEntity(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerDto.getId());
		customerEntity.setEmail(customerDto.getEmail());
		customerEntity.setAddress(customerDto.getAddress());
		customerEntity.setPassword(customerDto.getPassword());
		customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
		customerEntity.setUsername(customerDto.getUsername());
		return customerEntity;
	}
	
	default CustomerDto convertEntityToDTO(CustomerEntity customerEntity) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customerEntity.getId());
		customerDto.setEmail(customerEntity.getEmail());
		customerDto.setAddress(customerEntity.getAddress());
		customerDto.setPassword(customerEntity.getPassword());
		customerDto.setPhoneNumber(customerEntity.getPhoneNumber());
		customerDto.setUsername(customerEntity.getUsername());
		return customerDto;
	}

}
