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

import com.ecom.user.dto.BuyerDTO;
import com.ecom.user.service.BuyerService;
import com.ecom.user.dto.LoginDTO;

import org.springframework.core.env.Environment;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class BuyerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerService buyerService;
		
	@Autowired
	private Environment environment;
	
	
	// Fetches all buyers -1
	@GetMapping(value = "/buyers",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerDTO>> getAllBuyers() throws Exception {
		try {
			List<BuyerDTO> buyerDTO = buyerService.getAllBuyers();
			return new ResponseEntity<>(buyerDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}	
		
	// Fetches buyers by buyerId -2
	@GetMapping(value = "/buyers/{buyerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerDTO> getByBuyerId(@PathVariable String buyerId) throws Exception {
		try {
			BuyerDTO buyerDTO = buyerService.getByBuyerId(buyerId);
			return new ResponseEntity<>(buyerDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	
	
	// Buyer Login	-3
	@PostMapping(value = "/buyer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> LoginB(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
		try {
			buyerService.Login(loginDTO);
			String successMessage = environment.getProperty("API.BUYER_LOGIN_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	
	
	//Add Buyer -4
	@PostMapping(value = "/buyer",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBuyer(@Valid @RequestBody BuyerDTO buyerDTO) throws Exception {
		try {
			String buyerId = buyerService.addBuyer(buyerDTO);
			String successMessage = environment.getProperty("API.BUYER_INSERT_SUCCESS") + buyerId;
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	
	//Delete Buyer -5
	@DeleteMapping(value = "/dbuyer/{buyerId}")
	public ResponseEntity<String> deleteBuyer(@PathVariable String buyerId) throws Exception {
		try {
			buyerService.deleteBuyer(buyerId);
			String successMessage = environment.getProperty("API.DELETE_BUYER_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}	
	
}
