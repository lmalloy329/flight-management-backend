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
import com.lauren.springboot.model.Airport;
import com.lauren.springboot.repository.AirportRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class AirportController {
	@Autowired
	private AirportRepository airportRepository;
	
	//get single airport within controller
	private Airport findAirportByCode(String code) {
		List<Airport> airports = airportRepository.findAll();
		Airport airport = null;
		for(Airport a : airports) {
			if(a.getAirportCode().equalsIgnoreCase(code)) {
				airport=a;
			}
		}
		return airport;
	}
	
	//get all airports
	@GetMapping("/airports")
	public List<Airport> getAllAirports(){
		return airportRepository.findAll();
	
	}
	//create new airport
	@PostMapping("/airports")
	public Airport createAirport(@RequestBody Airport airport) {
		return airportRepository.save(airport);
	}
	//get airport by id
	@GetMapping("/airports/{code}")
	public ResponseEntity<Airport> getAirportById(@PathVariable String code){
		Airport airport = findAirportByCode(code);
		if(airport.equals(null)) {
			throw new ResourceNotFoundException("An Airport with code" + code+ "could not be found");
		}
		return ResponseEntity.ok(airport);
		
	}
	//update airport
	@PutMapping("/airports/{code}")
	public ResponseEntity<Airport> updateAirport(@PathVariable String code, @RequestBody Airport updatesForAirport){
		Airport airport = findAirportByCode(code);
		if(airport.equals(null)) {
			throw new ResourceNotFoundException("An Airport with code" + code+ "could not be found");
		}
		airport.setAirportCode(updatesForAirport.getAirportCode());
		airport.setAirportName(updatesForAirport.getAirportName());
		airport.setAirportLocation(updatesForAirport.getAirportLocation());
		
		Airport updatedAirport = airportRepository.save(airport);
		return ResponseEntity.ok(updatedAirport);
		
	}
	//delete Airport
	@DeleteMapping("/airports/{code}")
	public ResponseEntity<Map<String, Boolean>> deleteAirport(@PathVariable String code){
		Airport airport = findAirportByCode(code);
		if(airport.equals(null)) {
			throw new ResourceNotFoundException("An Airport with code" + code+ "could not be found");
		}
		airportRepository.delete(airport);
		Map<String, Boolean> response = new HashMap<>();
		return ResponseEntity.ok(response);
		
	}

	
	
}

