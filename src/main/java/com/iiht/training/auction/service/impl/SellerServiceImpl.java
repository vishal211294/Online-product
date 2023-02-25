package com.iiht.training.auction.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.SellerDto;
import com.iiht.training.auction.entity.SellerEntity;
import com.iiht.training.auction.exceptions.SellerNotFoundException;
import com.iiht.training.auction.repository.SellerRepository;
import com.iiht.training.auction.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private EntityManager entityManager;
	
	public SellerDto registerSeller(SellerDto sellerDto) {
		return convertEntityToDto(sellerRepository.save(convertDtoToEntity(sellerDto)));
	}

	public SellerDto updateSeller(SellerDto sellerDto) {
		getSellerEntity(sellerDto.getSellerId());
		return convertEntityToDto(entityManager.merge(convertDtoToEntity(sellerDto)));
	}

	public Boolean deleteSeller(Long sellerId) {
		getSellerEntity(sellerId);
		sellerRepository.deleteById(sellerId);
		return true;
	}

	public SellerDto getSellerById(Long sellerId) {
		return convertEntityToDto(getSellerEntity(sellerId));
	}

	public List<SellerDto> getAllSellers() {
		return sellerRepository.findAll().stream().map(entity -> convertEntityToDto(entity)).toList();
	}

	private SellerEntity getSellerEntity(Long id) {
		return sellerRepository.findById(id).orElseThrow(()-> new SellerNotFoundException());
	}

}
