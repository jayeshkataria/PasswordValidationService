package com.jk.passwordvalidationservice.rules;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jk.passwordvalidationservice.validator.ValidationResult;

public class NoRepeatingCharacterSequences extends AbstractValidationRule {

	/*
	 * The regex below will look for a repeating sequence. If any is found then return false
	 */
	private static final String REGEX_ATLEAST_ONE_REPEATING_SEQUENCE = "(.+?)\\1";
	
	private static final Pattern PATTERN = Pattern.compile(REGEX_ATLEAST_ONE_REPEATING_SEQUENCE); 
	
	public static final String FAIL_MESSAGE = "Password must not contain any repeating sequences of characters";
	
	private final static Logger LOGGER = Logger.getLogger(NoRepeatingCharacterSequences.class.getName());
	
	static {
		LOGGER.setLevel(Level.OFF);
	}
	
	@Override
	public ValidationResult validatePassword(final String password) {
		
		this.checkExceptions(password);
		
		ValidationResult result = new ValidationResult(false, this.getFailureMessage());
				
		Matcher m = PATTERN.matcher(password);
		
		if(m.find()){
			LOGGER.info("For " + password + ": " + m.groupCount() + ": " +  m.group(0) + " --> " + m.group(1));
		}
		else{
			result.setSuccess(true);
			result.setValidationMessage("SUCCESS");
		}
		
		return result;
	}

	@Override
	public String getFailureMessage() {
		return FAIL_MESSAGE;
	}

}
