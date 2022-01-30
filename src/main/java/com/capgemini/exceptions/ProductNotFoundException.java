package com.capgemini.exceptions;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(long id) {
		super("Could not find product " + id);
	}

}
