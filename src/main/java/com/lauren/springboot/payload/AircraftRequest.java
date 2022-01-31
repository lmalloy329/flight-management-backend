package com.lauren.springboot.payload;



public class AircraftRequest {

	private String aircraftCode;
	

	private long firstClass;
	
	private long firstPrice;
	

	private long businessClass;
	
	
	private long businessPrice;
	

	private long economyClass;
	
	private long economyPrice;


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
