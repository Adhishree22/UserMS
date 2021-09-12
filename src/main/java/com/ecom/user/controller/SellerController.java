package com.ecom.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecom.user.dto.SellerDTO;
import com.ecom.user.service.SellerService;
import com.ecom.user.dto.LoginDTO;

import org.springframework.core.env.Environment;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	private Environment environment;
	
	
	// Fetches all sellers -6
	@GetMapping(value = "/sellers",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerDTO>> getAllSellers() throws Exception {
		try {
			List<SellerDTO> sellerDTO = sellerService.getAllSellers();
			return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}	
		
	
	// Fetches sellers by sellerId -7
	@GetMapping(value = "/sellers/{sellerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SellerDTO>> getBySellerId(@PathVariable String sellerId) throws Exception {
		try {
			List<SellerDTO> sellerDTO = sellerService.getBySellerId(sellerId);
			return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	// Seller Login	-8
	@PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> LoginS(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		try {
			sellerService.Login(loginDTO);
			String successMessage = environment.getProperty("API.SELLER_LOGIN_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	
	
	//Add Seller -9
	@PostMapping(value = "/seller",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSeller(@Valid @RequestBody SellerDTO sellerDTO) throws Exception {
		try {
			String sellerId = sellerService.addSeller(sellerDTO);
			String successMessage = environment.getProperty("API.SELLER_INSERT_SUCCESS") + sellerId;
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	//Delete Seller -10
	@DeleteMapping(value = "/dseller/{sellerId}")
	public ResponseEntity<String> deleteSeller(@PathVariable String sellerId) throws Exception {
		try {
			sellerService.deleteSeller(sellerId);
			String successMessage = environment.getProperty("API.DELETE_SELLER_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	
}
