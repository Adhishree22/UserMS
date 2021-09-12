package com.ecom.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, String>{

	Optional<Wishlist> findByBuyerIdAndProdId(String buyerId, String prodId);
	void deleteByBuyerIdAndProdId(String buyerId, String prodId);
	List<Wishlist> findByBuyerId(String buyerId);
	

}
