package com.ecom.user.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "wishlist")
@IdClass(WishlistId.class)
public class Wishlist {

	@Id
	@Column(name = "buyer_id", nullable = false,length = 10)
	String buyerId;
	@Id
	@Column(name = "prod_id", nullable = false,length = 10)
	String prodId;
		
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


	public Wishlist() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerId, prodId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wishlist other = (Wishlist) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(prodId, other.prodId);
	}

}
