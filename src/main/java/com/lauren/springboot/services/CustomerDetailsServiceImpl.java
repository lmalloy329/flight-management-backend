package com.lauren.springboot.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lauren.springboot.model.Customer;
import com.lauren.springboot.repository.CustomerRepository;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Customer customer = customerRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with email:"+username));
		return CustomerDetailsImpl.build(customer);	}

	

	
}
