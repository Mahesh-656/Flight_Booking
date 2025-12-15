package org.flightbooking.exception;

public class NoResultFoundException extends RuntimeException {

	public NoResultFoundException() {
		
	}
	
	public NoResultFoundException(String message) {
		super(message);
	}

}
