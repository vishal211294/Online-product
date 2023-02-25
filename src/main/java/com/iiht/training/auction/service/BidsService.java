
package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.BidsDto;
import com.iiht.training.auction.entity.BidsEntity;

public interface BidsService {

	public BidsDto placeBid(BidsDto bidsDto);
	public List<BidsDto> getAllBidsOnProduct(Long porductId);
	public List<BidsDto> getAllBidsAfterProductBiddingEndDate(Long porductId);
	
	default BidsEntity convertDtoTOEntity(BidsDto bidsDto) {
		BidsEntity bidsEntity = new BidsEntity();
		bidsEntity.setBidAmount(bidsEntity.getBidAmount());
		bidsEntity.setBiddingDate(bidsDto.getBiddingDate());
		bidsEntity.setCustomerId(bidsDto.getCustomerId());
		bidsEntity.setId(bidsEntity.getId());
		bidsEntity.setProductId(bidsEntity.getProductId());
		return bidsEntity;
	}
	
	default BidsDto convertEntityToDto(BidsEntity bidsDto) {
		BidsDto bidsEntity = new BidsDto();
		bidsEntity.setBidAmount(bidsEntity.getBidAmount());
		bidsEntity.setBiddingDate(bidsDto.getBiddingDate());
		bidsEntity.setCustomerId(bidsDto.getCustomerId());
		bidsEntity.setId(bidsEntity.getId());
		bidsEntity.setProductId(bidsEntity.getProductId());
		return bidsEntity;
	}
}
