package com.lauren.springboot.payload;

import java.util.Set;

import javax.validation.constraints.*;
public class SignUpRequest {
	@NotBlank
	@Size(max=20)
	private String firstName;
	
	@NotBlank
	@Size(max=20)
	private String middleName;
	
	@NotBlank
	@Size(max=20)
	private String lastName;
	
	@NotBlank
	@Size(max=10)
	private String phoneNumber;
	
	
	@NotBlank
	@Size(min=3, max=20)
	private String email;
	
	@NotBlank
	@Size(max=50)
	private String address;
	
	@NotBlank
	@Size(max=20)
	private String city;
	
	
	@NotBlank
	@Size(max=20)
	private String state;
	
	@NotBlank
	@Size(max=20)
	private String country;
	
	private Set<String> role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
