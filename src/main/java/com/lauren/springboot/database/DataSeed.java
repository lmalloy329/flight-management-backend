package com.lauren.springboot.database;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lauren.springboot.exception.ResourceNotFoundException;
import com.lauren.springboot.model.Aircraft;
import com.lauren.springboot.model.Airport;
import com.lauren.springboot.model.Customer;
import com.lauren.springboot.model.ERole;
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.model.Role;
import com.lauren.springboot.repository.AircraftRepository;
import com.lauren.springboot.repository.AirportRepository;
import com.lauren.springboot.repository.CustomerRepository;
import com.lauren.springboot.repository.FlightRepository;
import com.lauren.springboot.repository.RoleRepository;

@Component
public class DataSeed implements CommandLineRunner{
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired 
	AircraftRepository aircraftRepo;
	
	@Autowired
	AirportRepository airportRepo;
	
	@Autowired
	FlightRepository flightRepo;

	@Override
	public void run(String... args) throws Exception{
		createRoles();
		loadCustomerData();
		loadAircraftData();
		loadAirportData();
		loadFlightData();
	}

	//creates data in Roles chart fromt he Enum values
	public void createRoles() {
		if(roleRepo.count()==0) {
			for(ERole role: ERole.values()) {
				Role user = new Role(role);
				roleRepo.save(user);
			}
		}
		System.out.println("roles:" +roleRepo.count());
	}
	
	//creates users with set roles
	public void loadCustomerData() {
		Set<Role> employee = new HashSet<>();
		employee.add(roleRepo.getById(2));
		Set<Role> customer = new HashSet<>();	
		customer.add(roleRepo.getById(1));
		
		if(custRepo.count()==0) {
			Customer cust1 = new Customer("Lauren", "Marie", "Malloy", "9176233543", "Lmalloy329@gmail.com", "204-50th ave","Breezy Point", "NY", "USA", "1234567" );
			cust1.setRoles(employee);
			custRepo.save(cust1);
			Customer cust2 = new Customer("Valerie", "Rose", "Good", "7184746051", "vg12@gmail.com", "133 Bay Road","Roxbury", "NY", "USA", "1234567" );	
			cust2.setRoles(customer);
			custRepo.save(cust2);
		}
		System.out.println("Custopmers: "+custRepo.count());
	}
	//Creates Aircrafts
	public void loadAircraftData() {
		if(aircraftRepo.count()==0) {
			Set<Aircraft> aircrafts= new HashSet<>();
			aircrafts.add(new Aircraft("330", 8 , 30 , 177));
			aircrafts.add(new Aircraft("747", 8 , 80 , 244));
			aircrafts.add(new Aircraft("320", 0 , 28 , 126));
			aircrafts.add(new Aircraft("900", 0 , 11 , 68));
			
			aircrafts.forEach(aircraft->{
				aircraftRepo.save(aircraft);
			});
		}
		
		System.out.println("Aircrafts: "+ aircraftRepo.count());
		
	}
	
	public void loadAirportData() {
		if(airportRepo.count()==0){
			Set<Airport> airports = new HashSet<>();
			airports.add(new Airport("JFK", "John F Kennedy International Airport", "New York, New York"));
			airports.add(new Airport("BOS", "Boston Logan International Airport", "Boston, Massachusetts"));
//			airports.add(new Airport("LHR", "Heathrow Airport", "London"));
			airports.add(new Airport("LAX", "Los Angeles International Airport", "Los Angeles, California"));
			airports.add(new Airport("ATL", "Hartsfield-Jackson International Airport", "Atlanta, Georgia"));
			airports.add(new Airport("DFW", "Dallas/Fort Worth International Airport", "Dallas, Texas"));
			airports.add(new Airport("DEN", "Denver International Airport", "Denver, Colorado"));
			airports.add(new Airport("ORD", "O'Hare International Airport", "Chicago, Illinois"));
			airports.add(new Airport("CLT", "Charlotte Douglas International Airport", "Charlotte, North Carolina"));
			airports.add(new Airport("LAS", "Harry Reid International Airport", "Las Vegas, Nevada"));
			airports.add(new Airport("PHX", "Phoenix Sky Harbor International Airport", "Phoenix, Arizona"));
			airports.add(new Airport("MCO", "Orlando International Airport", "Orlando, Florida"));
			airports.add(new Airport("SEA", "Seattle–Tacoma International Airport", "Seattle, Washington"));
			airports.add(new Airport("MIA", "Miami International Airport", "Miami, Florida"));
			airports.add(new Airport("IAH", "George Bush Intercontinental Airport", "Houston, Texas"));
			airports.add(new Airport("FLL", "Fort Lauderdale–Hollywood International Airport", "Fort Lauderale, Florida"));
			airports.add(new Airport("EWR", "Newark Liberty International Airport", "New York, New York"));
			airports.add(new Airport("SFO", "San Francisco International Airport", "San Francisco, California"));
			airports.add(new Airport("MSP", "Minneapolis–Saint Paul International Airport", "Minneapolis Minnesota"));
			airports.add(new Airport("DTW", "Detroit Metropolitan Airport", "Detorit, Michigan"));
			airports.add(new Airport("SLC", "Salt Lake City International Airport", "Salt Lake City, Utah"));
			airports.add(new Airport("PHL", "Philadelphia International Airport", "Philadelphia, Pennsylvania"));
			airports.add(new Airport("BWI", "Baltimore/Washington International Airport", "Blatimore, Maryland"));
			airports.add(new Airport("TPA", "Tampa International Airport", "Tampa Bay, Florida"));
			airports.add(new Airport("SAN", "San Diego International Airport", "San Diego, California"));
			airports.add(new Airport("MDW", "Midway International Airport", "Chicago, Illinois"));
			airports.add(new Airport("LGA", "LaGuardia Airport", "New York, New York"));
			airports.add(new Airport("BNA", "Nashville International Airport", "Nashville, Tennessee"));
			airports.add(new Airport("IAD", "Washington Dulles International Airport", "Washington, D.C."));
			
			
			airports.forEach(airport->{
				airportRepo.save(airport);
			});
	
		}
			System.out.println("Airports: "+airportRepo.count());
	}
	public Set<Airport> getAirport(String code){
		Set<Airport> airport = new HashSet<>();
		airport.add(airportRepo.findByAirportCode(code).orElseThrow(()-> new ResourceNotFoundException("Cannot find Airport")));
		return airport;
	}
	
	public void loadFlightData() {
		if(flightRepo.count()==0) {
			LocalDate dt = LocalDate.now();
			Date date = new Date();
			for(int i =0;i<10;i++) {
			
			Flight f1 = new Flight("JetBlue", date, date, 100.00);
			f1.setOriginAirport(getAirport("JFK"));
			f1.setDestinationAirport(getAirport("LAX"));
			f1.setAircraft(aircraftRepo.findByAircraftCode("747").orElseThrow(()-> new ResourceNotFoundException("Cannot find Aircraft")));
			flightRepo.save(f1);
//			Flight f2 = new Flight("United", date, date, 150.00);
//			f2.setOriginAirport(getAirport(""));
//			f2.setDestinationAirport(getAirport("LAX"));
//			f1.setAircraft(aircraftRepo.findByAircraftCode("330").orElseThrow(()-> new ResourceNotFoundException("Cannot find Aircraft")));
//			flightRepo.save(f2);
			}
			System.out.println("flights: " + flightRepo.count());
		}
		
	}

}
