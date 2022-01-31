package com.lauren.springboot.payload;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FlightRequest {
	@NotBlank
	@Size(max=20)
	private String airline;
	
	private String originAirport;
	
	private String destinationAirport;
	
	LocalDateTime departureDate;

	LocalDateTime arrivalDate;
	
	private double flightCost;
	
	private String aircraftCode;

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
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

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public double getFlightCost() {
		return flightCost;
	}

	public void setFlightCost(double flightCost) {
		this.flightCost = flightCost;
	}
	
	
	
}
