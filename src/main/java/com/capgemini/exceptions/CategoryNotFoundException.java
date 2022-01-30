package com.capgemini.exceptions;

public class CategoryNotFoundException extends RuntimeException {
	CategoryNotFoundException(long id) {
		super("Could not find category " + id);
	}

}
