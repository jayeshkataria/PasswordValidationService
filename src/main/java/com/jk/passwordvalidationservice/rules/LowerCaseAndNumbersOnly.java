package com.jk.passwordvalidationservice.rules;

import java.util.regex.Pattern;

import com.jk.passwordvalidationservice.validator.ValidationResult;

public class LowerCaseAndNumbersOnly extends AbstractValidationRule {

	private static final String REGEX_LOWER_CASE_AND_NUMBERS_ONLY = "[a-z0-9]+";
	
	private static final Pattern PATTERN = Pattern.compile(REGEX_LOWER_CASE_AND_NUMBERS_ONLY);
	
	public static final String FAIL_MESSAGE = "Password can only contain lowercase letters and numbers";
	
	public ValidationResult validatePassword(final String password){
		
		ValidationResult result = new ValidationResult(false, this.getFailureMessage());
		
		this.checkExceptions(password);

		if(PATTERN.matcher(password).matches()){
			result.setSuccess(true);
			result.setValidationMessage("");
		}
		return result;		
	}

	@Override
	public String getFailureMessage() {
		return FAIL_MESSAGE;
	}
}
