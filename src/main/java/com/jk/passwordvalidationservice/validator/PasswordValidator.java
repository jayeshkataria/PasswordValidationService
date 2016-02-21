package com.jk.passwordvalidationservice.validator;

import java.util.Collections;
import java.util.List;

import com.jk.passwordvalidationservice.exceptions.RuleSetupException;
import com.jk.passwordvalidationservice.rules.AbstractValidationRule;


public class PasswordValidator {
	
	private final List<AbstractValidationRule> rules;
	
	public PasswordValidator(final List<AbstractValidationRule> rules) {
		
		if(rules == null) {
			throw new NullPointerException("List of rules cannot be null");
		}
		
		if(rules.size() == 0){
			throw new RuleSetupException("There must be atleast one rule in the list");
		}
		
		this.rules = rules;
	}
	
	public List<AbstractValidationRule> getRules() {
		return Collections.unmodifiableList(rules);
	}

	public ValidationResult validatePassword(final String password) {
		
		ValidationResult result = new ValidationResult(false, "");
		
		StringBuilder failureMessages = new StringBuilder();
		
		boolean ruleFailed = false;
		
		for(AbstractValidationRule rule : this.getRules()){
			ValidationResult currResult = rule.validatePassword(password);
			
			if(!currResult.isSuccess()){
				failureMessages.append(currResult.getValidationMessage()).append(", ");
				ruleFailed = true;
			}
		}
		
		result.setSuccess(!ruleFailed);
		result.setValidationMessage(failureMessages.toString());
		
		return result;
		
	}
	
}
