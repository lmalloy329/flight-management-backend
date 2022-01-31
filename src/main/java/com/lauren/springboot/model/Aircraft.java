package com.lauren.springboot.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="aircraft")
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="aircraftCode")
	private String aircraftCode;

	@JsonIgnore
	@OneToMany(mappedBy="aircraft", cascade = CascadeType.ALL)
	private Set<Flight>flights;
	
	@Column(name="firstClass")
	private long firstClass;
	
	@Column(name="firstPrice")
	private long firstPrice;
	
	@Column(name="businessClass")
	private long businessClass;
	
	@Column(name="businessPrice")
	private long businessPrice;
	
	@Column(name="economyClass")
	private long economyClass;
	
	@Column(name="economyPrice")
	private long economyPrice;
	

	public Aircraft() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//constructor 
	public Aircraft(String aircraftCode, long firstClass, long firstPrice, long businessClass, long businessPrice, long economyClass, long economyPrice) {
		super();
		this.aircraftCode = aircraftCode;
		this.firstClass = firstClass;
		this.firstPrice = firstPrice;
		this.businessClass = businessClass;
		this.businessPrice= businessPrice;
		this.economyClass = economyClass;
		this.economyPrice = economyPrice;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public long getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(long firstClass) {
		this.firstClass = firstClass;
	}

	public long getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(long businessClass) {
		this.businessClass = businessClass;
	}

	public long getEconomyClass() {
		return economyClass;
	}

	public void setEconomyClass(long economyClass) {
		this.economyClass = economyClass;
	}



	public long getFirstPrice() {
		return firstPrice;
	}



	public void setFirstPrice(long firstPrice) {
		this.firstPrice = firstPrice;
	}



	public long getBusinessPrice() {
		return businessPrice;
	}



	public void setBusinessPrice(long businessPrice) {
		this.businessPrice = businessPrice;
	}



	public long getEconomyPrice() {
		return economyPrice;
	}



	public void setEconomyPrice(long economyPrice) {
		this.economyPrice = economyPrice;
	}
	
	
	
	
}
