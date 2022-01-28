package com.capgemini.exceptions;

public class ProductNotFoundException extends RuntimeException {
	ProductNotFoundException(long id) {
		super("Could not find product " + id);
	}

}
