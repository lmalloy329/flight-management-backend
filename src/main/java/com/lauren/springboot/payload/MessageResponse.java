package com.lauren.springboot.payload;

public class MessageResponse {
	private String mesaage;

	public MessageResponse(String mesaage) {
		super();
		this.mesaage = mesaage;
	}

	public String getMesaage() {
		return mesaage;
	}

	public void setMesaage(String mesaage) {
		this.mesaage = mesaage;
	}
	
	
}
