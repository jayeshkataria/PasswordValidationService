package com.jk.passwordvalidationservice.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 2637217172195985510L;
	
	public ValidationException(String message){
		super(message);
	}

}
