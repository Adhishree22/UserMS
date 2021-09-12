package com.ecom.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>{

	Optional<Cart> findByBuyerIdAndProdId(String buyerId, String prodId);
	void deleteByBuyerIdAndProdId(String buyerId, String prodId);
	List<Cart> findByBuyerId(String buyerId);
}
