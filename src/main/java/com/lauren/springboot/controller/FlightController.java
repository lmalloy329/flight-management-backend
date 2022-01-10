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
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.repository.FlightRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class FlightController {
	@Autowired
	private FlightRepository flightRepository;
//get all flights
	@GetMapping("/flights")
	public List<Flight> getAllFlights(){
		return flightRepository.findAll();
	}
	//create new flight
	@PostMapping("/flights")
	public Flight createFlight(@RequestBody Flight flight){
		return flightRepository.save(flight);
	}
	//get flights by id
	@GetMapping("/flights/{id}")
	public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
		Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flight with Id:" + id + " does not exist."));
		return ResponseEntity.ok(flight);
	}
	//update flights
	@PutMapping("/flights/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight updatesForFlight){
		Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flight with Id:" + id + " does not exist."));
	 flight.setAircraftCode(updatesForFlight.getAircraftCode());
	 flight.setAirline(updatesForFlight.getAirline());
	 flight.setDestinationAirport(updatesForFlight.getDestinationAirport());
	 flight.setOriginAirport(updatesForFlight.getOriginAirport());
	 Flight updatedFlight = flightRepository.save(flight);
	 return ResponseEntity.ok(updatedFlight);
	
	
	}
	//delete flight
	@DeleteMapping("/flights/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFlight(@PathVariable Long id){
		Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flight with Id:" + id + " does not exist."));
		flightRepository.delete(flight);
		Map<String, Boolean> response= new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	
	}
	
	}