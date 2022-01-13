package com.lauren.springboot.payload;

import javax.validation.constraints.NotBlank;

import com.lauren.springboot.model.Customer;
import com.lauren.springboot.model.Flight;

public class ReservationRequest {
	@NotBlank
	private Customer customer;
	
	@NotBlank
	private Flight flight;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	

}
