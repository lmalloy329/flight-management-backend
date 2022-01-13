package com.lauren.springboot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="aircraft")
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="aircraftCode")
	private String aircraftCode;
	
	@OneToMany(mappedBy="aircraft")
	private Set<Flight>flights;
	
	@Column(name="firstClass")
	private long firstClass;
	
	@Column(name="businessClass")
	private long businessClass;
	
	@Column(name="economyClass")
	private long economyClass;
	
	

	public Aircraft() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Aircraft(String aircraftCode, long firstClass, long businessClass, long economyClass) {
		super();
		this.aircraftCode = aircraftCode;
		this.firstClass = firstClass;
		this.businessClass = businessClass;
		this.economyClass = economyClass;
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
	
	
	
	
}
