package com.iiht.training.auction.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.CustomerDto;
import com.iiht.training.auction.entity.CustomerEntity;
import com.iiht.training.auction.exceptions.CustomerNotFoundException;
import com.iiht.training.auction.repository.CustomerRepository;
import com.iiht.training.auction.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EntityManager entityManager;

	
	public CustomerDto registerCustomer(CustomerDto customerDto) {
		return convertEntityToDTO(customerRepository.save(convertDTOToEntity(customerDto)));
	}

	public CustomerDto updateCustomer(CustomerDto customerDto) {
		isCutomerExist(customerDto.getId());
		CustomerEntity customerEntity = convertDTOToEntity(customerDto);
		customerEntity = entityManager.merge(customerEntity);
		return convertEntityToDTO(customerEntity);
	}

	public Boolean deleteCustomer(Long id) {
		isCutomerExist(id);
		customerRepository.deleteById(id);
		return true;
	}

	public CustomerDto getCustomerById(Long id) {
		return convertEntityToDTO(isCutomerExist(id));
	}

	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll().stream().map(entity -> convertEntityToDTO(entity)).toList();
	}

	private CustomerEntity isCutomerExist(Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException());
	}
}
