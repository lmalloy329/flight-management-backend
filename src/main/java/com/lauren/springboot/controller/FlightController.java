package com.lauren.springboot.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

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
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.payload.FlightRequest;
import com.lauren.springboot.payload.MessageResponse;
import com.lauren.springboot.repository.AirportRepository;
import com.lauren.springboot.repository.FlightRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/test/")
public class FlightController {
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private AirportRepository airportRepository;
	
//get all flights
	@GetMapping("/flights")
	public List<Flight> getAllFlights(){
		return flightRepository.findAll();
	}
	
	//create new flight
	@PostMapping("newFlight")
	public ResponseEntity<?> registerFlight(@Valid @RequestBody FlightRequest flightRequest){
		//create new account
		Flight flight = new Flight(flightRequest.getAirline(), flightRequest.getAircraftCode());
		Set<String> originAirport = flightRequest.getOriginAirport();
		Set<Airport> originAirports = new HashSet<>();
		Set<String> destinationAirport = flightRequest.getDestinationAirport();
		Set<Airport> destinationAirports = new HashSet<>();

		
		if(originAirport==null) {
			new RuntimeException("Error: Please assign airport");
		
		} else {
			originAirport.forEach(airport->{
				Airport origin = airportRepository.findByAirportCode(airport).orElseThrow(()-> new RuntimeException("Could not find origin airport" + airport));
				
				originAirports.add(origin);
			});
			
			destinationAirport.forEach(airport ->{
				Airport destination = airportRepository.findByAirportCode(airport).orElseThrow(()-> new RuntimeException("Coudl not find destination ariport" + airport));
				destinationAirports.add(destination);
			});
		}
		flight.setOriginAirport(originAirports);
		flight.setDestinationAirport(destinationAirports);
		flightRepository.save(flight);
		return ResponseEntity.ok(new MessageResponse("Flight Registered Succesfully!"));
		
	
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