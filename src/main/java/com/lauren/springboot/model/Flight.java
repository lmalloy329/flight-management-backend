package com.lauren.springboot.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@Column(name="airline")
	private String airline;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="origin_table", 
				joinColumns = @JoinColumn(name = "flight_id"), 
				inverseJoinColumns = @JoinColumn(name = "airport_id"))
	private Set<Airport> originAirport = new HashSet<>();

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="destination_table",
				joinColumns = @JoinColumn(name = "flight_id"), 
				inverseJoinColumns = @JoinColumn(name = "airport_id"))
		private Set<Airport> destinationAirport =new HashSet<>();;
	
	@Column(name="aircraft")
	private String aircraftCode;
	
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String airline, String aircraftCode) {
		super();
		this.airline = airline;
		this.aircraftCode = aircraftCode;
	}


	

	public Set<Airport> getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Set<Airport> originAirport) {
		this.originAirport = originAirport;
	}

	public Set<Airport> getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Set<Airport> destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}



	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}

	
	
}
