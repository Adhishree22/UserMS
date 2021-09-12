package com.ecom.user.dto;

import com.ecom.user.entity.Wishlist;

public class WishlistDTO {
	
	String prodId;
	String buyerId;
	
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public WishlistDTO() {
		super();
	}

	@Override
	public String toString() {
		return "SubscribedProductDTO [prodId=" + prodId + ", buyerId=" + buyerId + "]";
	}

	// Converts Entity into DTO
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setProdId(wishlist.getProdId());
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
						
		return wishlistDTO;
	}
	
	// Converts DTO into Entity
	public Wishlist createWishlist() {
		Wishlist wishlist = new Wishlist();
		wishlist.setProdId(this.getProdId());
		wishlist.setBuyerId(this.getBuyerId());
		
		return wishlist;
	}
	
}