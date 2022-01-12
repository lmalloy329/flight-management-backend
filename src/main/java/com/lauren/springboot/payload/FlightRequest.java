package com.lauren.springboot.payload;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FlightRequest {
	@NotBlank
	@Size(max=20)
	private String airline;
	
	private Set<String> originAirport;
	
	private Set<String> destinationAirport;
	
	@NotBlank
	@Size(max= 3)
	private String aircraftCode;

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

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}
	
	
}
