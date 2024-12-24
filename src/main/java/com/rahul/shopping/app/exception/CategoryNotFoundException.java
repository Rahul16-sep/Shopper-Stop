package com.rahul.shopping.app.exception;

public class CategoryNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(String message) {
		super(message);
	}

}
