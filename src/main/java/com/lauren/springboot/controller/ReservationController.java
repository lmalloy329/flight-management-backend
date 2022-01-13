package com.lauren.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.model.Reservation;
import com.lauren.springboot.payload.MessageResponse;
import com.lauren.springboot.payload.ReservationRequest;
import com.lauren.springboot.repository.ReservationRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;
	
	
	//get all reservations
	@GetMapping("/reservations")
	public List<Reservation> getAllReservations(){
		return reservationRepository.findAll();
	}
	
	//Create new reservation
	@PostMapping("reservations")
	public ResponseEntity<?> createReservation(@RequestBody ReservationRequest resRequest){
		Reservation reservation = new Reservation(resRequest.getCustomer(), resRequest.getFlight());
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Resercation has been succesfully created"));
	}
}
