package com.lauren.springboot.model;


import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToOne
	@JoinColumn(name="aircraftCode")
	private Aircraft aircraft;
	
	@OneToMany(mappedBy="flight")
	private Set<Reservation> reservations;
	
	@Column(name="departureDate")
	 Date departureDate;
	
	@Column(name="arrivalTime")
	Date arrivalDate;
	
	@Column(name="flightCost")
	private double flightCost;
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Flight( String airline, Date departureDate,
			Date arrivalDate, double flightCost) {
		super();
		this.airline = airline;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.flightCost = flightCost;
	}



	public Aircraft getAircraft() {
		return aircraft;
	}



	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}



	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}



	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public double getFlightCost() {
		return flightCost;
	}

	public void setFlightCost(double flightCost) {
		this.flightCost = flightCost;
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
