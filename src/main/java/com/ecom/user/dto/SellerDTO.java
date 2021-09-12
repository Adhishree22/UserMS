package com.ecom.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ecom.user.entity.Seller;

public class SellerDTO {
	
	@NotNull
	String sellerId;
	@NotNull
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and spaces")
	String name;
	@NotNull
	@Email(message = "Please provide a valid email address")
	String email;
	//@Size(min = 10,max= 10, message = "phoneNo should be exact 10 characters." )
	@Pattern(regexp="(^$|[0-9]{10})")
	String phoneNo;
	@NotNull
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#!@$%^&*]).{7,19}$", 
			message = "It should be 7 to 20 characters in length (both inclusive). It should contain at least one uppercase, at least one lowercase, at least one digit. "
					+ "It should also contain a special character amongst -! @, #, $, %, ^, &, *")
	String password;
	boolean isactive;

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	
	// Converts Entity into DTO
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerId(seller.getSellerId());
		sellerDTO.setName(seller.getName());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setPhoneNo(seller.getPhoneNo());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setIsactive(seller.isIsactive());
						
		return sellerDTO;
	}
	
	// Converts DTO into Entity
	public Seller createSeller() {
		Seller seller = new Seller();
		seller.setSellerId(this.getSellerId());
		seller.setName(this.getName());
		seller.setEmail(this.getEmail());
		seller.setPhoneNo(this.getPhoneNo());
		seller.setPassword(this.getPassword());
		seller.setIsactive(this.isIsactive());
		
		return seller;
	}
	
}