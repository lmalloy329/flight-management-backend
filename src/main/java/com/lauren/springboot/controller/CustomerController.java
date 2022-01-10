package com.lauren.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.exception.ResourceNotFoundException;
import com.lauren.springboot.model.Customer;
import com.lauren.springboot.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//get all Customers
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	//create Customer
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	//get Customer by ID
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerByID(@PathVariable Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer with Id:" + id + " does not exist."));
		return ResponseEntity.ok(customer);
	}
	//update Customer
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatesForCustomer){
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer with Id:" + id + " does not exist."));
		
		customer.setFirstName(updatesForCustomer.getFirstName());
		customer.setMiddleName(updatesForCustomer.getMiddleName());
		customer.setLastName(updatesForCustomer.getLastName());
		customer.setAddress(updatesForCustomer.getAddress());
		customer.setPhoneNumber(updatesForCustomer.getPhoneNumber());
		customer.setEmail(updatesForCustomer.getEmail());
		customer.setCountry(updatesForCustomer.getCountry());
		customer.setState(updatesForCustomer.getState());
		customer.setRoles(updatesForCustomer.getRoles());
		customer.setCity(updatesForCustomer.getCity());
		customer.setPassword(updatesForCustomer.getPassword());
		Customer  updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updatedCustomer);
		
	}
	
	//delete customer
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
		
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer with Id:" + id + " does not exist."));
		
		customerRepository.delete(customer);
		
		 Map<String, Boolean> response= new HashMap<>();
		 response.put("Deleted", Boolean.TRUE);
		 return ResponseEntity.ok(response);
	}
	
}
