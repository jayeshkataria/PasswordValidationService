package com.jk.passwordvalidationservice.rules;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jk.passwordvalidationservice.App;
import com.jk.passwordvalidationservice.exceptions.RuleSetupException;
import com.jk.passwordvalidationservice.exceptions.ValidationException;

public class LengthBetweenRangeInclusiveTest {

	private static final String STRING_SPACE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 abcdefghijklmnopqrstuvwxyz";
	
	private final static Logger LOGGER = Logger.getLogger(LengthBetweenRangeInclusiveTest.class.getName()); 
	
	private static LengthBetweenRangeInclusive rule;	

	public LengthBetweenRangeInclusiveTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(App.SPRING_CONFIG_PATH)){
			rule = (LengthBetweenRangeInclusive) context.getBean("LengthBetweenRangeInclusiveTest");
		}
		
		LOGGER.setLevel(Level.OFF);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		rule = null;
	}

	@Test(expected=ValidationException.class)
	public void testValidationException(){
		rule.validatePassword(null);
	}
	
	@Test(expected=RuleSetupException.class)
	public void testRuleSetupExceptionInvalidLower(){
		new LengthBetweenRangeInclusive(-1, 10);
	}
	
	@Test(expected=RuleSetupException.class)
	public void testRuleSetupExceptionLowerGreaterThanUpper(){
		new LengthBetweenRangeInclusive(5, 4);
	}
	
	@Test
	public void testRuleSetupExceptionValidRange(){
		new LengthBetweenRangeInclusive(0, 8);
	}
	
	@Test
	public void testRuleSetupExceptionLowerEqualsUpper(){
		new LengthBetweenRangeInclusive(0, 0);
	}
	
	@Test
	public void testEmptyString() { 
		Assert.assertFalse(rule.validatePassword("").isSuccess());
	}
	
	@Test
	public void testOneBelowLowerBound(){
		String pass = generateString(rule.getLower() - 1);
		LOGGER.info(getPassInfo(pass));
		Assert.assertFalse(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testAtLowerBound(){
		String pass = generateString(rule.getLower());
		LOGGER.info(getPassInfo(pass));
		Assert.assertTrue(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testOneAboveLowerBound(){
		String pass = generateString(rule.getLower() + 1);
		LOGGER.info(getPassInfo(pass));
		Assert.assertTrue(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testOneBelowUpperBound(){
		String pass = generateString(rule.getUpper() - 1);
		LOGGER.info(getPassInfo(pass));
		Assert.assertTrue(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testAtUpperBound(){
		String pass = generateString(rule.getUpper());
		LOGGER.info(getPassInfo(pass));
		Assert.assertTrue(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testOneAboveUpperBound(){
		String pass = generateString(rule.getUpper() + 1);
		LOGGER.info(getPassInfo(pass));
		Assert.assertFalse(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testAnyBetweenRange() { 
		String pass = generateString((rule.getLower() + rule.getUpper()) / 2);
		LOGGER.info(getPassInfo(pass));
		Assert.assertTrue(rule.validatePassword(pass).isSuccess());
	}
	
	@Test
	public void testEmptySpacesBetweenRange() { 
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= (rule.getLower() + rule.getUpper()) / 2; i++){
			sb.append(" ");
		}
		LOGGER.info(getPassInfo(sb.toString()));
		Assert.assertTrue(rule.validatePassword(sb.toString()).isSuccess());
	}
	
	
	private static String generateString(int length){
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= length; i++){
			int character = (int)(Math.random()*STRING_SPACE.length());
			sb.append(STRING_SPACE.charAt(character));
			
		}
		return sb.toString();
	}
	
	private static String getPassInfo(String pass){
		return "Password: '" + pass + "' of length " + pass.length();
	}
	
}
