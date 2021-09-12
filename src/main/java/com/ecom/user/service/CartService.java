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

import com.ecom.user.dto.CartDTO;
import com.ecom.user.entity.Buyer;
import com.ecom.user.entity.Cart;
import com.ecom.user.repository.BuyerRepository;
import com.ecom.user.repository.CartRepository;

@Service
@Transactional
public class CartService {
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerRepository buyerRepo;

	@Autowired
	CartRepository cartRepo;
	
	//Add products to Cart
	public String addProductCart(CartDTO cartDTO) throws Exception {
	
		Cart cartEntity = cartDTO.createCart();
		Optional<Buyer> buyer = buyerRepo.findByBuyerId(cartEntity.getBuyerId());
		buyer.orElseThrow(() -> new Exception("Service.BUYER_NOT_FOUND"));				
		cartRepo.save(cartEntity);		
		return cartEntity.getBuyerId();
	
	}
	
	//Delete Product from Cart
	public void deleteProductCart(String buyerId, String prodId) throws Exception {
		Optional<Cart> cart = cartRepo.findByBuyerIdAndProdId(buyerId,prodId);
		cart.orElseThrow(() -> new Exception("Service.PRODUCT_NOT_FOUND"));
		cartRepo.deleteByBuyerIdAndProdId(buyerId,prodId);
	}
	
	//Get Cart by buyerId
	public List<CartDTO> getAllProductsCart(@PathVariable String buyerId) throws Exception{

		List<Cart> carts = cartRepo.findByBuyerId(buyerId);
		List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
			for (Cart cart : carts) {
				CartDTO cartDTO = CartDTO.valueOf(cart);
				cartDTOs.add(cartDTO);
		}
			logger.info("Buyer details : {}", cartDTOs);
			if (cartDTOs.isEmpty()){
				throw new Exception("Service.PRODUCT_NOT_FOUND");
			}
			return cartDTOs;
		}
	
}
