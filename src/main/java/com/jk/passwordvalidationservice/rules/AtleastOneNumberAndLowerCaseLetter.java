package com.jk.passwordvalidationservice.rules;

import java.util.regex.Pattern;

import com.jk.passwordvalidationservice.validator.ValidationResult;

public class AtleastOneNumberAndLowerCaseLetter extends AbstractValidationRule {

	private static Pattern LOWERCASE = Pattern.compile("[a-z]");
	
	private static Pattern NUMBER = Pattern.compile("\\d");
	
	public static final String FAIL_MESSAGE = "Password must contain atleast one lowercase letter and one number";
	
	@Override
	public ValidationResult validatePassword(final String password) {

		this.checkExceptions(password);

		ValidationResult result = new ValidationResult(false, this.getFailureMessage());
		
		boolean lcase = LOWERCASE.matcher(password).find();

		boolean num = NUMBER.matcher(password).find();
		
		if(lcase && num){
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
