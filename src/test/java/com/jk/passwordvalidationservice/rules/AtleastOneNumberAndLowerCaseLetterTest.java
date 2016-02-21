package com.jk.passwordvalidationservice.rules;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jk.passwordvalidationservice.App;
import com.jk.passwordvalidationservice.exceptions.ValidationException;

public class AtleastOneNumberAndLowerCaseLetterTest {

	private static AtleastOneNumberAndLowerCaseLetter rule;
	
	public AtleastOneNumberAndLowerCaseLetterTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(App.SPRING_CONFIG_PATH)){
			rule = (AtleastOneNumberAndLowerCaseLetter) context.getBean("AtleastOneNumberAndLowerCaseLetterTest");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		rule = null;
	}
	
	
	
	
	@Test(expected=ValidationException.class)
	public void testValidationException(){
		rule.validatePassword(null);
	}
	
	@Test
	public void testEmptyString() { 
		Assert.assertFalse(rule.validatePassword("").isSuccess());
	}
		
	@Test
	public void testSingleLowerase() {
		Assert.assertFalse(rule.validatePassword("f").isSuccess());
	}
	
	@Test
	public void testSingleNumber() {
		Assert.assertFalse(rule.validatePassword("5").isSuccess());
	}
	
	@Test
	public void testSpaceAndNumber() {
		Assert.assertFalse(rule.validatePassword(" 8").isSuccess());
	}
	
	@Test
	public void testSingleLowercaseAndNumber(){
		Assert.assertTrue(rule.validatePassword("a6").isSuccess());
	}
	
	@Test
	public void testSingleNumberAndLowercase(){
		Assert.assertTrue(rule.validatePassword("7b").isSuccess());
	}
	
	@Test
	public void testCleanLowercaseAndNumberCombination(){
		Assert.assertTrue(rule.validatePassword("a68bd3g").isSuccess());
	}
	
	@Test
	public void testAlphanumericComboWithRuleSatisfaction(){
		Assert.assertTrue(rule.validatePassword("aA7g5S#D$F$!Ibd3g").isSuccess());
	}
}
