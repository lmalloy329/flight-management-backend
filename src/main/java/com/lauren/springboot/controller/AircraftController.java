package com.lauren.springboot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lauren.springboot.exception.ResourceNotFoundException;
import com.lauren.springboot.model.Aircraft;
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.payload.AircraftRequest;
import com.lauren.springboot.payload.MessageResponse;
import com.lauren.springboot.repository.AircraftRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class AircraftController {
	@Autowired
	private AircraftRepository aircraftRepository;
	
	//get all aircraft
	@GetMapping("/aircrafts")
	public List<Aircraft> getAllAircraft(){
		return aircraftRepository.findAll();
	}
	
	//create new aircraft
	@PostMapping("/aircrafts")
	public ResponseEntity<?> createAircraft(@RequestBody AircraftRequest acRequest) {
		Aircraft aircraft = new Aircraft(acRequest.getAircraftCode(), acRequest.getFirstClass(), acRequest.getBusinessClass(), acRequest.getEconomyClass());
		Set<Flight> flights= new HashSet<>();
		aircraft.setFlights(flights);
		
		aircraftRepository.save(aircraft);
		
		return ResponseEntity.ok(new MessageResponse("Aircraft was succesfully Created!" + aircraft));
		
	}
	//Get flight by code
	@GetMapping("/aircrafts/{code}")
	public ResponseEntity<?> getAircraftByCode(@PathVariable String code){
		Aircraft aircraft = aircraftRepository.findByAircraftCode(code).orElseThrow(()-> new ResourceNotFoundException("Aircraft with code" + code+ "not found"));
		return ResponseEntity.ok(aircraft);
	}
	//Update aircraft
	@PutMapping("/aircrafts/{code}")
	public ResponseEntity<?> updateAircraft(@PathVariable String code, @RequestBody Aircraft updatesForAircraft){
		Aircraft aircraft = aircraftRepository.findByAircraftCode(code).orElseThrow(()-> new ResourceNotFoundException("Aircraft with code" + code+ "not found"));
		aircraft.setAircraftCode(updatesForAircraft.getAircraftCode());
		aircraft.setBusinessClass(updatesForAircraft.getBusinessClass());
		aircraft.setEconomyClass(updatesForAircraft.getEconomyClass());
		aircraft.setFirstClass(updatesForAircraft.getFirstClass());
		Aircraft updatedAircraft = aircraftRepository.save(aircraft);
		return ResponseEntity.ok(updatedAircraft);
	}
}
