package com.jk.passwordvalidationservice.validator;

public class ValidationResult {
	
	protected boolean success;
	
	protected String validationMessage;
	
	public ValidationResult() {
		
	}
	
	public ValidationResult(final boolean success, final String validationMessage) {
		this.success = success;
		this.validationMessage = validationMessage;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

	public String getValidationMessage() {
		return validationMessage;
	}
	
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	
}
