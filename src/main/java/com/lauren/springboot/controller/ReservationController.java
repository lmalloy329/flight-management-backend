package com.lauren.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.exception.ResourceNotFoundException;
import com.lauren.springboot.model.Payment;
import com.lauren.springboot.model.Reservation;
import com.lauren.springboot.repository.CustomerRepository;
import com.lauren.springboot.repository.FlightRepository;
import com.lauren.springboot.repository.PaymentRepository;
import com.lauren.springboot.repository.ReservationRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	
	//get all reservations
	@GetMapping("/reservations")
	public List<Reservation> getAllReservations(){
		return reservationRepository.findAll();
	}
	
	//Create new reservation
	@PostMapping("/reservations/f/{fId}/c/{cId}")
	public Reservation createReservation(@PathVariable long fId, @PathVariable long cId){
		Reservation reservation = new Reservation((custRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Cannot Find Resources"))),flightRepo.findById(fId).orElseThrow(()-> new ResourceNotFoundException("Cannot Find Resources")));
		Payment payment = new Payment("cash", new Date());
		payment.setReservation(reservation);
		paymentRepo.save(payment);
		reservation.setPayment(payment);
		return reservationRepository.save(reservation);	
	}

}
