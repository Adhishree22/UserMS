package com.ecom.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, String>{

	Optional<Buyer> findByBuyerId(String buyerId);
	List<Buyer> getByBuyerId(String buyerId);
	Buyer findByEmail(String email);
	Object findByPhoneNo(String phoneNo);
	


}
