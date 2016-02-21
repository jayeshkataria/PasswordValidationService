package com.jk.passwordvalidationservice.rules;

import com.jk.passwordvalidationservice.exceptions.ValidationException;
import com.jk.passwordvalidationservice.validator.ValidationResult;

public abstract class AbstractValidationRule {
	
	
	public abstract ValidationResult validatePassword(final String password);
	
	public abstract String getFailureMessage();
	
	protected void checkExceptions(final String password){
		if(password == null){
			throw new ValidationException("Password string cannot be null");
		}
	}
	
}
