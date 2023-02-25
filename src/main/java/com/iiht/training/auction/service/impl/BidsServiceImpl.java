package com.iiht.training.auction.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.auction.dto.BidsDto;
import com.iiht.training.auction.entity.BidsEntity;
import com.iiht.training.auction.entity.ProductEntity;
import com.iiht.training.auction.exceptions.ProductNotFoundException;
import com.iiht.training.auction.repository.BidsRepository;
import com.iiht.training.auction.repository.ProductRepository;
import com.iiht.training.auction.service.BidsService;

@Service
public class BidsServiceImpl implements BidsService {

	@Autowired
	private BidsRepository bidsRepository;

	@Autowired
	private ProductRepository productRepository;

	public BidsDto placeBid(BidsDto bidsDto) {
		getProductEntity(bidsDto.getProductId());
		return convertEntityToDto(bidsRepository.save(convertDtoTOEntity(bidsDto)));
	}

	public List<BidsDto> getAllBidsOnProduct(Long porductId) {
		getProductEntity(porductId);
		return getBidsByProduct(porductId).stream().map(entity -> convertEntityToDto(entity)).toList();
	}

	public List<BidsDto> getAllBidsAfterProductBiddingEndDate(Long porductId) {
		ProductEntity productEntity = getProductEntity(porductId);
		return bidsRepository.findByProductIdAndBiddingDate(productEntity.getLastDateOfBidding(), porductId).stream().map(entity -> convertEntityToDto(entity)).toList();
	}
	
	private ProductEntity getProductEntity(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
	}
	
	private List<BidsEntity> getBidsByProduct(Long id) {
		return bidsRepository.findByProductId(id);
	}

}
