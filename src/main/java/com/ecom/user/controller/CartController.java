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
import com.ecom.user.dto.CartDTO;
import com.ecom.user.service.CartService;

import org.springframework.core.env.Environment;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CartController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CartService cartService;
	
	@Autowired
	private Environment environment;
	
	@Value("${prod.uri}")
	String prodUri;
	
	//Add Products To Cart -11
	@PostMapping(value = "/cart",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductCart(@RequestBody CartDTO cartDTO) throws Exception {
		try {
			ProductDTO prodDTO = new RestTemplate().getForObject(prodUri+cartDTO.getProdId(), ProductDTO.class);
			if(prodDTO == null) {
				throw new Exception("API.NO_PRODUCT_FOUND");
			}
			cartService.addProductCart(cartDTO);
			String successMessage = environment.getProperty("API.ADD_CART_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}
	
	//Delete Products from Cart -12
	@DeleteMapping(value = "/cart/{buyerId}/{prodId}")
	public ResponseEntity<String> deleteProductCart(@PathVariable String buyerId,@PathVariable String prodId) throws Exception{
		try {
			cartService.deleteProductCart(buyerId,prodId);
			String successMessage = environment.getProperty("API.DELETE_CART_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	
	}
	
	// Fetches all products in cart by buyerId
	@GetMapping(value = "/cart/{buyerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartDTO>> getAllProductsCart(@PathVariable String buyerId) throws Exception {
		try {
			List<CartDTO> cartDTO = cartService.getAllProductsCart(buyerId);
			return new ResponseEntity<>(cartDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()), exception);
		}
	}


	
}
