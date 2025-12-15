package org.flightbooking.exception;

public class NoPassengerFoundException extends RuntimeException{

	public NoPassengerFoundException() {

	}

	public NoPassengerFoundException(String message) {
		super(message);
	}

}
