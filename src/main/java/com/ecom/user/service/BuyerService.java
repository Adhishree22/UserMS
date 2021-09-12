package com.ecom.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.user.dto.BuyerDTO;
import com.ecom.user.dto.LoginDTO;
import com.ecom.user.entity.Buyer;
import com.ecom.user.repository.BuyerRepository;
import com.ecom.user.repository.CartRepository;


@Service
public class BuyerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerRepository buyerRepo;

	@Autowired
	CartRepository cartRepo;
		
	// Fetches buyer details by buyerId
	public BuyerDTO getByBuyerId(@PathVariable String buyerId) throws Exception{

		Optional<Buyer> buyers = buyerRepo.findByBuyerId(buyerId);
		Buyer buyer = buyers.orElseThrow(() -> new Exception("Service.BUYER_NOT_FOUND"));
		BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
		return buyerDTO;
	}
	
	
	// Fetches all buyers details
		public List<BuyerDTO> getAllBuyers() throws Exception{

			List<Buyer> buyers = buyerRepo.findAll();
			List<BuyerDTO> buyerDTOs = new ArrayList<BuyerDTO>();

			for (Buyer buyer : buyers) {
				BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
				buyerDTOs.add(buyerDTO);
			}

				logger.info("Buyer details : {}", buyerDTOs);
				if (buyerDTOs.isEmpty()){
					throw new Exception("Service.BUYERS_NOT_FOUND");
				}
				return buyerDTOs;
			}
		
	// Buyer Login	
	public boolean Login(LoginDTO loginDTO) throws Exception{
		logger.info("Login request for seller {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
		Buyer buyer = buyerRepo.findByEmail(loginDTO.getEmail());
			if (buyer != null && buyer.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}else {
				throw new Exception("Service.LOGIN_DETAILS_NOT_FOUND");
			}
	}


	//Buyer Registration
	public String addBuyer(BuyerDTO buyerDTO) throws Exception {
		Buyer buyerEntity = buyerDTO.createBuyer();
		Optional<Buyer> buyers = buyerRepo.findByBuyerId(buyerEntity.getBuyerId());
		if(buyers.isPresent() ) {
			throw new Exception("Buyer Id Already Exists");
		}else if(buyerRepo.findByEmail(buyerEntity.getEmail()) != null) {
			throw new Exception("Buyer email Already Exists");
		}
		else if(buyerRepo.findByPhoneNo(buyerEntity.getPhoneNo()) != null) {
			throw new Exception("Buyer phone number Already Exists");
		}else {
			buyerRepo.save(buyerEntity);
			return buyerEntity.getBuyerId();			
		}
		
	}

	//Delete Buyer's account
	public void deleteBuyer(String buyerId) throws Exception {
		Optional<Buyer> buyer = buyerRepo.findByBuyerId(buyerId);
		buyer.orElseThrow(() -> new Exception("Service.BUYER_NOT_FOUND"));
		buyerRepo.deleteById(buyerId);	
	}
		
}
