package com.lauren.springboot.services;



import java.util.Collection;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lauren.springboot.model.Customer;

public class CustomerDetailsImpl implements UserDetails{

	private static final long serialVersionUID =1L;
	
	private long id;

	private String firstName;

	private String middleName;
	
	private String lastName;

	private String phoneNumber;

	private String email;
	
	private String address;
	
	private String city;

	private String state;
	
	private String country;
	
	@JsonIgnore
	private String password;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public CustomerDetailsImpl(long id, String firstName, String middleName, String lastName, String phoneNumber,
			String email, String address, String city, String state, String country, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomerDetailsImpl build(Customer customer) {
		List<GrantedAuthority> authorities = customer.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new CustomerDetailsImpl(
				customer.getId(),
				customer.getFirstName(),
				customer.getMiddleName(),
				customer.getLastName(),
				customer.getPhoneNumber(),
				customer.getEmail(),
				customer.getAddress(), 
				customer.getCity(),
				customer.getState(),
				customer.getPassword(),
				customer.getCountry(),
				authorities);
	}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public static Long getSerialversopmiod() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPassword() {
		return password;
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomerDetailsImpl customer = (CustomerDetailsImpl) o;
		return Objects.equals(id, customer.id);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
}
