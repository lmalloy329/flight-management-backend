package com.lauren.springboot.payload;



public class AircraftRequest {

	private String aircraftCode;
	

	private long firstClass;
	

	private long businessClass;
	

	private long economyClass;


	public String getAircraftCode() {
		return aircraftCode;
	}


	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
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
