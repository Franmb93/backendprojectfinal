package com.capgemini.exceptions;

public class DealNotFoundException extends RuntimeException {
	public DealNotFoundException(long id) {
		super("Could not find deal " + id);
	}

}
