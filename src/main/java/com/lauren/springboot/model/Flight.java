package com.lauren.springboot.model;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	

	@ManyToOne
	@JoinColumn(name="aircraftCode")
	private Aircraft aircraft;
	
	@JsonIgnore
	@OneToMany(mappedBy="flight")
	private Set<Reservation> reservations;
	
	@Column(name="departureDate")
	 LocalDateTime departureDate;
	
	@Column(name="arrivalTime")
	LocalDateTime arrivalDate;
	
	
	@Column(name="firstTickets")
	private long firstTickets;
	
	@Column(name="businessTickets")
	private long businessTickets;
	
	@Column(name="economyTickets")
	private long economyTickets;
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Flight( String airline, LocalDateTime departureDate,
			LocalDateTime arrivalDate) {
		super();
		this.airline = airline;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.firstTickets=0;
		this.businessTickets=0;
		this.economyTickets=0;
	}



	public Aircraft getAircraft() {
		return aircraft;
	}



	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}



	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}



	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}


	public long getFirstTickets() {
		return firstTickets;
	}



	public void setFirstTickets(long firstTickets) {
		this.firstTickets = firstTickets;
	}



	public long getBusinessTickets() {
		return businessTickets;
	}



	public void setBusinessTickets(long businessTickets) {
		this.businessTickets = businessTickets;
	}



	public long getEconomyTickets() {
		return economyTickets;
	}



	public void setEconomyTickets(long economyTickets) {
		this.economyTickets = economyTickets;
	}



	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
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

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	

	
	
}
