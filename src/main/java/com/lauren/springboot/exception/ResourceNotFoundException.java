package com.lauren.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1l;
	//create error to throw when we cannot find a resource
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
