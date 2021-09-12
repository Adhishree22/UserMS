package com.ecom.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String>{

	Optional<Seller> findBySellerId(String sellerId);
	List<Seller> getBySellerId(String sellerId);
	Seller findByEmail(String email);
	Object findByPhoneNo(String phoneNo);

}
