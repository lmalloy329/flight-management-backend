package com.lauren.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reservationId;
	
	
	@ManyToOne
	@JoinColumn(name="CusotmerId", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="FlightId", nullable=false)
	private Flight flight;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Payment payment;
	
	public Reservation() {}
	
	

	public Reservation(Customer customer, Flight flight) {
		super();
		this.customer = customer;
		this.flight = flight;
	}



	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

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



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	
}