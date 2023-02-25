package com.iiht.training.auction.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.training.auction.entity.BidsEntity;

@Repository
public interface BidsRepository extends JpaRepository<BidsEntity, Long> {

	List<BidsEntity> findByProductId(Long productId);
	
	@Query("select a from Bids a where a.biddingDate > :bidLastDate and a.productId = :productId")
    List<BidsEntity> findByProductIdAndBiddingDate(@Param("bidLastDate") LocalDate bidLastDate, @Param("productId") Long productId);
}
