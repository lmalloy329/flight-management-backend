package com.lauren.springboot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reservationId;
	
	@NotBlank
	@Column(name="seatClass")
	private String seatClass;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CustomerId", nullable = false)
	private Customer customer;
	


	@ManyToOne
	@JoinColumn(name="FlightId", nullable=false)
	private Flight flight;
	
	@OneToOne
	@JoinColumn(name="paymentId")
	private Payment payment;
	
	
	public Reservation() {}
	
	

	public Reservation(Customer customer, Flight flight, String seatClass) {	
		super();
		this.customer = customer;
		this.flight = flight;
		this.seatClass = seatClass;
	}



	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", seatClass=" + seatClass + ", customer=" + customer
				+ ", flight=" + flight + ", payment=" + payment + "]";
	}



	public String getSeatClass() {
		return seatClass;
	}



	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
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
