package com.lauren.springboot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.lauren.springboot.jwt.JwtUtils;
import com.lauren.springboot.model.Customer;
import com.lauren.springboot.model.ERole;
import com.lauren.springboot.model.Role;
import com.lauren.springboot.payload.JwtResponse;
import com.lauren.springboot.payload.LoginRequest;
import com.lauren.springboot.payload.MessageResponse;
import com.lauren.springboot.payload.SignUpRequest;
import com.lauren.springboot.repository.CustomerRepository;
import com.lauren.springboot.repository.RoleRepository;
import com.lauren.springboot.services.CustomerDetailsImpl;

@CrossOrigin(origins = "http://localhost:3000/", maxAge=3600)
@RestController
@RequestMapping("/api/auth/")

public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	//for when i fix securirty
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt =jwtUtils.generateJwtToken(authentication);
		CustomerDetailsImpl customerDetails =(CustomerDetailsImpl) authentication.getPrincipal();
		List<String> roles = customerDetails.getAuthorities().stream().map((item)->item.getAuthority()).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, customerDetails.getId(), customerDetails.getFirstName(), customerDetails.getMiddleName(), customerDetails.getLastName(), customerDetails.getPhoneNumber(), customerDetails.getEmail(), customerDetails.getAddress(), customerDetails.getCity(), customerDetails.getState(), customerDetails.getCountry(), roles));
		
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignUpRequest signUpRequest){
		//check if email is used
		if(customerRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:Username is already Taken"));
		}
		//create new account
//	for when i fix encoder	Customer customer = new Customer(signUpRequest.getFirstName(), signUpRequest.getMiddleName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(), signUpRequest.getEmail(), signUpRequest.getAddress(), signUpRequest.getCity(), signUpRequest.getState(), signUpRequest.getCountry(), encoder.encode(signUpRequest.getPassword()));
		Customer customer = new Customer(signUpRequest.getFirstName(), signUpRequest.getMiddleName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(), signUpRequest.getEmail(), signUpRequest.getAddress(), signUpRequest.getCity(), signUpRequest.getState(), signUpRequest.getCountry(), signUpRequest.getPassword());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoles==null) {
			Role customerRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()-> new RuntimeException("Error: Role is not found."));
			roles.add(customerRole);
		} else {
			
			strRoles.forEach(role->{
				switch(role) {
				case"employee":
					Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE).orElseThrow(()-> new RuntimeException("Error: Role is not found."));
					roles.add(employeeRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()-> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}	
			});
		}
		customer.setRoles(roles);
		customerRepository.save(customer);
		return ResponseEntity.ok(new MessageResponse("User Registered Succesfully!"));
		
	}
}
