package com.lauren.springboot.database;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
//			System.out.print(employee);
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
			aircrafts.add(new Aircraft("330", 8 , 450, 30 , 300, 177, 100));
			aircrafts.add(new Aircraft("747", 8 , 500, 80 , 250, 244, 75));
			aircrafts.add(new Aircraft("320", 0 , 0, 28, 260 , 126, 100));
			aircrafts.add(new Aircraft("900", 0 ,0, 11, 150 , 68, 75));
			
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
			List<Airport> airportsOrigin = new ArrayList<>();
				airportsOrigin = airportRepo.findAll();
			List<Airport> airportsDestination = new ArrayList<>();
				airportsDestination = airportRepo.findAll();
			for(Airport origin: airportsOrigin) {	
				airportsDestination.remove(0);
				for(Airport destination: airportsDestination) {
			LocalDateTime dateOneA = LocalDateTime.of(2022, Month.JANUARY, 21, 8, 00);
			LocalDateTime dateOneB = dateOneA.plusHours(3);
			LocalDateTime dateTwoA = LocalDateTime.of(2022, Month.JANUARY,22, 8, 00);
			LocalDateTime dateTwoB = dateTwoA.plusHours(3);
			for(int i =0;i<30;i++) {
				for(int j= 0; j<2;j++) {
			Flight f1 = new Flight("JetBlue",dateOneA, dateOneB);
			f1.setOriginAirport(getAirport(origin.getAirportCode()));
			f1.setDestinationAirport(getAirport(destination.getAirportCode()));
			f1.setAircraft(aircraftRepo.findByAircraftCode("747").orElseThrow(()-> new ResourceNotFoundException("Cannot find Aircraft")));
			flightRepo.save(f1);
			Flight f2 = new Flight("JetBlue",dateTwoA, dateTwoB);
			f2.setOriginAirport(getAirport(destination.getAirportCode()));
			f2.setDestinationAirport(getAirport(origin.getAirportCode()));
			f2.setAircraft(aircraftRepo.findByAircraftCode("747").orElseThrow(()-> new ResourceNotFoundException("Cannot find Aircraft")));
			flightRepo.save(f2);
			dateOneA = dateOneA.plusHours(2);
			dateOneB = dateOneB.plusHours(2);
			dateTwoA = dateTwoB.plusHours(2); 
			dateTwoB = dateTwoB.plusHours(2);
				}
			dateOneA = dateOneA.plusDays(2).minusHours(8);
			dateOneB = dateOneB.plusDays(2).minusHours(8);
			dateTwoA = dateTwoB.plusDays(2).minusHours(8); 
			dateTwoB = dateTwoB.plusDays(2).minusHours(8);
			}
			}};
		}
		System.out.println("flights: " + flightRepo.count());
	}

}
