package com.lauren.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.model.Payment;
import com.lauren.springboot.repository.PaymentRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepo;
	
	//get all payments
	@GetMapping("/payments")
	public List<Payment> getAllPayments(){	
		return paymentRepo.findAll();
	}
	
	

}
