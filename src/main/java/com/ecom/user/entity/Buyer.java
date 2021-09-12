package com.ecom.user.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buyer")
public class Buyer {
	
	@Id
	@Column(name = "buyer_id",unique = true,nullable = false,length = 10)
	String buyerId;
	@Column(nullable = false, length = 50)
	String name;
	@Column(nullable = false,unique = true, length = 50)
	String email;
	@Column(name = "phone_no",unique = true,nullable = false)
	String phoneNo;
	@Column(nullable = false, length = 20)
	String password;
	@Column(nullable = false)
	boolean isprivileged;
	@Column(nullable = false)
	int rewardpoints;
	@Column(nullable = false)
	boolean isactive;
	
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public boolean isIsprivileged() {
		return isprivileged;
	}
	public void setIsprivileged(boolean isprivileged) {
		this.isprivileged = isprivileged;
	}
	public int getRewardpoints() {
		return rewardpoints;
	}
	public void setRewardpoints(int rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	public Buyer() {
		super();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(buyerId, email, isactive, isprivileged, name, password, phoneNo, rewardpoints);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buyer other = (Buyer) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(email, other.email)
				&& isactive == other.isactive && isprivileged == other.isprivileged && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && phoneNo == other.phoneNo
				&& rewardpoints == other.rewardpoints;
	}

	
	
	
}