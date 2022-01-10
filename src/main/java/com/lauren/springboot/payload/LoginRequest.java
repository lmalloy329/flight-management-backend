package com.lauren.springboot.payload;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginRequest {
	PasswordEncoder encode;
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		System.out.print(password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
