package com.ecom.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.user.dto.WishlistDTO;
import com.ecom.user.entity.Buyer;
import com.ecom.user.entity.Wishlist;
import com.ecom.user.repository.BuyerRepository;
import com.ecom.user.repository.WishlistRepository;

@Service
@Transactional
public class WishlistService {


	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerRepo;

	@Autowired
	WishlistRepository wishRepo;
	
	public String addProductWish(WishlistDTO wishDTO) throws Exception {
		
		Wishlist wishEntity = wishDTO.createWishlist();
		Optional<Buyer> buyer = buyerRepo.findByBuyerId(wishEntity.getBuyerId());
		buyer.orElseThrow(() -> new Exception("Service.BUYER_NOT_FOUND"));					
		wishRepo.save(wishEntity);		
		return wishEntity.getBuyerId();
	
	}
	
	public void deleteProductWish(String buyerId,String prodId) throws Exception {
		Optional<Wishlist> wish = wishRepo.findByBuyerIdAndProdId(buyerId,prodId);
		wish.orElseThrow(() -> new Exception("Service.PRODUCT_NOT_FOUND"));
		wishRepo.deleteByBuyerIdAndProdId(buyerId,prodId);
	}

	public List<WishlistDTO> getAllProductsWish(@PathVariable String buyerId) throws Exception{
		List<Wishlist> wishes = wishRepo.findByBuyerId(buyerId);
		List<WishlistDTO> wishDTOs = new ArrayList<WishlistDTO>();
			for (Wishlist wish : wishes) {
				WishlistDTO wishDTO = WishlistDTO.valueOf(wish);
				wishDTOs.add(wishDTO);
		}
			logger.info("Buyer details : {}", wishDTOs);
			if (wishDTOs.isEmpty()){
				throw new Exception("Service.PRODUCT_NOT_FOUND");
			}
			return wishDTOs;
	}

}


