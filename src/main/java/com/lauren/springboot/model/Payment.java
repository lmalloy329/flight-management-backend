package com.lauren.springboot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="payments")
public class Payment {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	@OneToOne(cascade= CascadeType.ALL, mappedBy="payment")
	private Reservation reservation;
	
	@Column(name="paymentMethod")
	private String paymentMethod;
	
	@Column(name="paymentDate")
	private Date date;

	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Payment(String paymentMethod, Date date) {
		super(); 
		this.paymentMethod = paymentMethod;
		this.date = date;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}	


