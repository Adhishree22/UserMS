package com.ecom.user.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seller")
public class Seller {
	
	@Id
	@Column(name = "seller_id",unique=true,nullable = false,length = 10)
	String sellerId;
	@Column(nullable = false, length = 50)
	String name;
	@Column(nullable = false,unique=true, length = 50)
	String email;
	@Column(name = "phone_no",unique=true,nullable = false)
	String phoneNo;
	@Column(nullable = false, length = 20)
	String password;
	@Column(nullable = false)
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
	
	
	
	public Seller() {
		super();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, isactive, name, password, phoneNo, sellerId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(email, other.email) && isactive == other.isactive && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && phoneNo == other.phoneNo
				&& Objects.equals(sellerId, other.sellerId);
	}
		
	

}
