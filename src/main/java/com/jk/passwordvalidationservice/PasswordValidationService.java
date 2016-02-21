package com.jk.passwordvalidationservice;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jk.passwordvalidationservice.validator.PasswordValidator;
import com.jk.passwordvalidationservice.validator.ValidationResult;

@Path("passwordvalidator")
public class PasswordValidationService {

	private static final PasswordValidator VALIDATOR;
	
	private final static Logger LOGGER = Logger.getLogger(PasswordValidationService.class.getName());
	
	static {
		try(ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext(App.SPRING_CONFIG_PATH)){
			VALIDATOR = (PasswordValidator) springContext.getBean("DefaultPasswordValidator");
		}
		
		if(VALIDATOR == null){
			throw new RuntimeException("Unable to access password validator");
		}
		
		LOGGER.setLevel(Level.INFO);
		
	}
	
	public PasswordValidationService(){
	}
	
	@GET
	@Path("validate")
	@Produces(MediaType.APPLICATION_JSON)
	public ValidationResult validatePassword(@QueryParam("password") final String password) {
		ValidationResult result = VALIDATOR.validatePassword(password); 
		LOGGER.info("Valiating: " + password + ": "+ result.isSuccess() + ": " + result.getValidationMessage());
		return result;
	}
	
}
