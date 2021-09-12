package com.ecom.user.dto;

import com.ecom.user.entity.Cart;

public class CartDTO {
	
	String buyerId;
	String prodId;
	int quantity;
	
	
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CartDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}

	// Converts Entity into DTO
	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerId(cart.getBuyerId());
		cartDTO.setProdId(cart.getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}

	public Cart createCart() {
		Cart cart = new Cart();
		cart.setBuyerId(this.getBuyerId());
		cart.setProdId(this.getProdId());
		cart.setQuantity(this.getQuantity());
		return cart;
	}
	
	
}