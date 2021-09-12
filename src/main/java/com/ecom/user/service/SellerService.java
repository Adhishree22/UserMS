package com.ecom.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.user.dto.LoginDTO;
import com.ecom.user.dto.SellerDTO;
import com.ecom.user.entity.Seller;
import com.ecom.user.repository.SellerRepository;


@Service
public class SellerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SellerRepository sellerRepo;

	// Fetches seller details by sellerId
		public List<SellerDTO> getBySellerId(@PathVariable String sellerId) throws Exception{

			List<Seller> sellers = sellerRepo.getBySellerId(sellerId);
			List<SellerDTO> sellerDTOs = new ArrayList<SellerDTO>();

			for (Seller seller : sellers) {
				SellerDTO buyerDTO = SellerDTO.valueOf(seller);
				sellerDTOs.add(buyerDTO);
			}
			
			logger.info("Seller details by sellerId : {}", sellerDTOs);
			if (sellerDTOs.isEmpty()){
				throw new Exception("Service.SELLER_NOT_FOUND");
			}
			return sellerDTOs;
		}
		
		// Fetches all seller details
			public List<SellerDTO> getAllSellers() throws Exception{

				List<Seller> sellers = sellerRepo.findAll();
				List<SellerDTO> sellerDTOs = new ArrayList<SellerDTO>();

				for (Seller seller : sellers) {
					SellerDTO sellerDTO = SellerDTO.valueOf(seller);
					sellerDTOs.add(sellerDTO);
				}

					logger.info("Seller details : {}", sellerDTOs);
					if (sellerDTOs.isEmpty()){
						throw new Exception("Service.SELLERS_NOT_FOUND");
					}
					return sellerDTOs;
			}
			
		// Seller Login	
		public boolean Login(LoginDTO loginDTO) throws Exception{
			logger.info("Login request for seller {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			Seller seller = sellerRepo.findByEmail(loginDTO.getEmail());
				if (seller != null && seller.getPassword().equals(loginDTO.getPassword())) {
					return true;
				}else {
					throw new Exception("Service.LOGIN_DETAILS_NOT_FOUND");
				}
		}


		//Seller Registration
		public String addSeller(SellerDTO sellerDTO) throws Exception {
			Seller sellerEntity = sellerDTO.createSeller();
			Optional<Seller> sellers = sellerRepo.findBySellerId(sellerEntity.getSellerId());			
			if(sellers.isPresent()) {
				throw new Exception("Seller Id Already Exists");
			}else if(sellerRepo.findByEmail(sellerEntity.getEmail()) != null) {
				throw new Exception("Seller email Already Exists");
			}
			else if(sellerRepo.findByPhoneNo(sellerEntity.getPhoneNo()) != null) {
				throw new Exception("Seller phone number Already Exists");
			}else {
				sellerRepo.save(sellerEntity);
				return sellerEntity.getSellerId();
			}
		}

		//Delete Seller's account
		public void deleteSeller(String sellerId) throws Exception {
			Optional<Seller> seller = sellerRepo.findBySellerId(sellerId);
			seller.orElseThrow(() -> new Exception("Service.SELLER_NOT_FOUND"));
			sellerRepo.deleteById(sellerId);	
		}

}
