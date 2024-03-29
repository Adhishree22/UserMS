package com.ecom.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginDTO {
	
	@NotNull
	@Email(message = "Please provide valid email address")
	String email;
	@NotNull(message = "Please provide valid password")
	String password;
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + "]";
	}	
	
}