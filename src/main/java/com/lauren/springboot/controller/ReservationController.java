package com.lauren.springboot.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.exception.ResourceNotFoundException;
import com.lauren.springboot.model.Customer;
import com.lauren.springboot.model.Flight;
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
	//get reservation at id
	@GetMapping("/reservations/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable long id){
		Reservation res = reservationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reservation with Id:" + id + " does not exist."));
		return	ResponseEntity.ok(res);
	}
	
	//Create new reservation
	@PostMapping("/reservations/flight/{fId}/customer/{cId}")
	public Reservation createReservation(@PathVariable long fId, @PathVariable long cId, @RequestBody String seatClass){
		seatClass = seatClass.substring(0, seatClass.length()-1);
		Flight flight = flightRepo.findById(fId).orElseThrow(()-> new ResourceNotFoundException("Cannot Find Resources"));
		//check what class and add ticket for class to flight
		if(seatClass.equals("First")) {
			flight.setFirstTickets(flight.getFirstTickets()+1);
		}else if(seatClass.equals("Business")) {
			flight.setBusinessTickets(flight.getBusinessTickets()+1);
		} else {
			flight.setEconomyTickets(flight.getEconomyTickets()+1);
		}
		flightRepo.save(flight);
		Reservation reservation = new Reservation((custRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Cannot Find Resources"))),flight, seatClass);
		Payment payment = new Payment("cash", new Date());
		payment.setReservation(reservation);
		paymentRepo.save(payment);
		reservation.setPayment(payment);
		
		return reservationRepository.save(reservation);	
	}
	//get reservation for customer
	@GetMapping("/reservations/cust/{id}")
	public List<Reservation> getCustomersReservations(@PathVariable long id){
		Customer cust = custRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reservations for this customer do not exist."));
		return reservationRepository.findByCustomer(cust);
	}
	//get reservation for flight
	@GetMapping("/reservations/flight/{id}")
	public List<Reservation> getFlightsReservations(@PathVariable long id){
		Flight flight = flightRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reservations for this customer do not exist."));
		return reservationRepository.findByFlight(flight);
	}
	//delete reservation
	@DeleteMapping("/reservations/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteReservation(@PathVariable long id){
		//need to figure out this issue with deleting with manyToMany relationships (solution CASCADE.remove?)
		Reservation res = reservationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reservation with Id:" + id + " does not exist."));
		System.out.print(res.getCustomer().getReservations());
//		res.getCustomer().setReservations(res.getCustomer().getReservations().filter(e->e.includes()));
		reservationRepository.delete(res);
		

		
		 Map<String, Boolean> response= new HashMap<>();
		 response.put("Deleted", Boolean.TRUE);
		 return ResponseEntity.ok(response);
	}
}
