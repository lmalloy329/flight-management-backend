package com.lauren.springboot.controller;

import java.time.LocalDateTime;
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
import com.lauren.springboot.model.Aircraft;
import com.lauren.springboot.model.Airport;
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.payload.FlightRequest;
import com.lauren.springboot.payload.MessageResponse;
import com.lauren.springboot.repository.AircraftRepository;
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
	
	@Autowired
	private AircraftRepository aircraftRepository;
//get all flights
	@GetMapping("/flights")
	public List<Flight> getAllFlights(){
		return flightRepository.findAll();
	}
	
	//create new flight
	@PostMapping("flights")
	public ResponseEntity<?> registerFlight(@Valid @RequestBody FlightRequest flightRequest){
		//create new account
		Flight flight = new Flight(flightRequest.getAirline(), flightRequest.getDepartureDate(), flightRequest.getArrivalDate());
		String originAirport = flightRequest.getOriginAirport();
		Set<Airport> originAirports = new HashSet<>();
		String destinationAirport = flightRequest.getDestinationAirport();
		Set<Airport> destinationAirports = new HashSet<>();
		String aircraftCode = flightRequest.getAircraftCode();

		//checks to add aircraft to res if it doesnt have one trows error
		if(aircraftCode==null) {
			new RuntimeException("Error: Please assign aircraft");
			flight.setAircraft(new Aircraft());
		} else {
		
				Aircraft ac= aircraftRepository.findByAircraftCode(aircraftCode).orElseThrow(()-> new RuntimeException("Could not find aircraft" + aircraftCode));;
				flight.setAircraft(ac);
			
		
		}
		//if these are not specified it sets them to now for date/ throws errorfor missing airports
		if(flight.getArrivalDate()==null) {
			LocalDateTime now = LocalDateTime.now();
			flight.setArrivalDate(now.plusHours(3));
		}
		if(flight.getDepartureDate()==null) {
			LocalDateTime now = LocalDateTime.now();
			flight.setDepartureDate(now);
		}
		if(originAirport==null || destinationAirport==null) {
			new RuntimeException("Error: Please assign airport");
		
		} else {

				Airport origin = airportRepository.findByAirportCode(originAirport).orElseThrow(()-> new RuntimeException("Could not find origin airport" + originAirport));
				
				originAirports.add(origin);

			

				Airport destination = airportRepository.findByAirportCode(destinationAirport).orElseThrow(()-> new RuntimeException("Coudl not find destination ariport" + destinationAirport));
				destinationAirports.add(destination);
		
		}
		
		flight.setOriginAirport(originAirports);
		flight.setDestinationAirport(destinationAirports);
		flightRepository.save(flight);
		return ResponseEntity.ok(new MessageResponse("Flight Registered Succesfully!"));
		
	
}

	//get flights by id
	@GetMapping("/flights/{id}")
	public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
		Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flight with Id:" + id + " does not exist."));
		return ResponseEntity.ok(flight);
	}
	//get flights by id
	@GetMapping("/flights/code/{origin}")
	public List<Flight> getFlightByOrigin(@PathVariable String origin){
		Airport airport = airportRepository.findByAirportCode(origin).orElseThrow(()-> new ResourceNotFoundException("Flight witht this airport does not exist."));
		return flightRepository.findByOriginAirport(airport);
		
	}
	//update flights
	@PutMapping("/flights/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight updatesForFlight){
		Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flight with Id:" + id + " does not exist."));
	 flight.setAircraft(updatesForFlight.getAircraft());
	 flight.setAirline(updatesForFlight.getAirline());
	 flight.setDestinationAirport(updatesForFlight.getDestinationAirport());
	 flight.setOriginAirport(updatesForFlight.getOriginAirport());
	 flight.setArrivalDate(updatesForFlight.getArrivalDate());

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