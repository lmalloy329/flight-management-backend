package com.lauren.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airports")
public class Airport {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long airportId;
	@Column(name="airportCode")
	private String airportCode;
	@Column(name="airportName")
	private String airportName;
	@Column(name="airportLocation")
	private String airportLocation;






	public Airport(String airportCode, String airportName, String airportLocation) {
		super();
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.airportLocation = airportLocation;
	}

 


	
	public Airport() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportCode=" + airportCode + ", airportName=" + airportName
				+ ", airportLocation=" + airportLocation + "]";
	}





	public String getAirportCode() {
		return airportCode;
	}


	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}


	public String getAirportName() {
		return airportName;
	}


	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}


	public String getAirportLocation() {
		return airportLocation;
	}


	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}
	
	

}
