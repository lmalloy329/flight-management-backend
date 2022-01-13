package com.lauren.springboot.payload;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FlightRequest {
	@NotBlank
	@Size(max=20)
	private String airline;
	
	private Set<String> originAirport;
	
	private Set<String> destinationAirport;
	
	Date departureDate;

	Date arrivalDate;
	
	private double flightCost;
	
	private Set<String> aircraftCode;

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Set<String> getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Set<String> originAirport) {
		this.originAirport = originAirport;
	}

	public Set<String> getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Set<String> destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Set<String> getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(Set<String> aircraftCode) {
		this.aircraftCode = aircraftCode;
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
	
	
	
}
