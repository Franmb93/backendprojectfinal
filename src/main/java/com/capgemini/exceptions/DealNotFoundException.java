package com.capgemini.exceptions;

public class DealNotFoundException extends RuntimeException {
	DealNotFoundException(long id) {
		super("Could not find deal " + id);
	}

}
