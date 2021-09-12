package com.ecom.user.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.ecom.user.dto.ProductDTO;
import com.ecom.user.dto.WishlistDTO;
import com.ecom.user.service.WishlistService;

import org.springframework.core.env.Environment;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class WishlistController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	WishlistService wishService;
	
	@Autowired
	private Environment environment;
	
	@Value("${prod.uri}")
	String prodUri;
	
	//Add Products To Wishlist -13
	@PostMapping(value = "/wish",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductWish(@RequestBody WishlistDTO wishDTO) throws Exception {
		try {			
			String addWish = wishService.addProductWish(wishDTO);			
			ProductDTO prodDTO = new RestTemplate().getForObject(prodUri+wishDTO.getProdId(), ProductDTO.class);
			if(prodDTO==null) {
				throw new Exception("API.PRODUCT_NOT_FOUND");
			}		
			String successMessage = environment.getProperty("API.ADD_WISH_SUCCESS") + addWish;
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	//Delete Product From Wishlist -14
	@DeleteMapping(value = "/wish/{buyerId}/{prodId}")
	public ResponseEntity<String> deleteProductWish(@PathVariable String buyerId,@PathVariable String prodId) throws Exception{
		try {
			wishService.deleteProductWish(buyerId,prodId);
			String successMessage = environment.getProperty("API.DELETE_WISH_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	
	}
	
	// Fetches all products in wishlist by buyerId
	@GetMapping(value = "/wish/{buyerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WishlistDTO>> getAllProductsWish(@PathVariable String buyerId) throws Exception {
		try {
			List<WishlistDTO> wishDTO = wishService.getAllProductsWish(buyerId);
			return new ResponseEntity<>(wishDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}

	
}
