package com.iiht.training.auction.service;

import java.util.List;

import com.iiht.training.auction.dto.SellerDto;
import com.iiht.training.auction.entity.SellerEntity;

public interface SellerService {

	public SellerDto registerSeller(SellerDto sellerDto);

	public SellerDto updateSeller(SellerDto sellerDto);

	public Boolean deleteSeller(Long sellerId);

	public SellerDto getSellerById(Long sellerId);

	public List<SellerDto> getAllSellers();

	default SellerDto convertEntityToDto(SellerEntity sellerEntity) {
		SellerDto sellerDto = new SellerDto();
		sellerDto.setAddress(sellerEntity.getAddress());
		sellerDto.setPhoneNumber(sellerEntity.getPhoneNumber());
		sellerDto.setSellerEmail(sellerEntity.getSellerEmail());
		sellerDto.setSellerId(sellerEntity.getSellerId());
		sellerDto.setSellerName(sellerEntity.getSellerName());
		return sellerDto;
	}
	
	default SellerEntity convertDtoToEntity(SellerDto sellerEntity) {
		SellerEntity sellerDto = new SellerEntity();
		sellerDto.setAddress(sellerEntity.getAddress());
		sellerDto.setPhoneNumber(sellerEntity.getPhoneNumber());
		sellerDto.setSellerEmail(sellerEntity.getSellerEmail());
		sellerDto.setSellerId(sellerEntity.getSellerId());
		sellerDto.setSellerName(sellerEntity.getSellerName());
		return sellerDto;
	}
}