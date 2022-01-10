package com.lauren.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lauren.springboot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer>  findByUsername(String username);

	Boolean existsByUsername(String username);
	
	Optional<Customer> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	

}
