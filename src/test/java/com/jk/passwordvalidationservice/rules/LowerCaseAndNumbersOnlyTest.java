package com.jk.passwordvalidationservice.rules;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jk.passwordvalidationservice.App;
import com.jk.passwordvalidationservice.exceptions.ValidationException;

public class LowerCaseAndNumbersOnlyTest {

	private static LowerCaseAndNumbersOnly rule;
	
	public LowerCaseAndNumbersOnlyTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(App.SPRING_CONFIG_PATH)){
			rule = (LowerCaseAndNumbersOnly) context.getBean("LowerCaseAndNumbersOnlyTest");
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
	public void testSpace() { 
		Assert.assertFalse(rule.validatePassword(" ").isSuccess());
	}
	
	@Test
	public void testAllUpperCase() {
		Assert.assertFalse(rule.validatePassword("AL").isSuccess());
	}
	
	@Test
	public void testUpperCaseAndNumber() {
		Assert.assertFalse(rule.validatePassword("Z1").isSuccess());
	}
	
	@Test
	public void testMixOfLowerUpperNumberAndSpecialChars() {
		Assert.assertFalse(rule.validatePassword("@dG12!Bca").isSuccess());
	}
	
	@Test
	public void testOnlyLowercaseLetters(){
		Assert.assertTrue(rule.validatePassword("lmn").isSuccess());
	}
	
	@Test
	public void testOnlyNumbers(){
		Assert.assertTrue(rule.validatePassword("6789").isSuccess());
	}
	
	@Test
	public void testLowecaseLettersFollwedByNumbers() {
		Assert.assertTrue(rule.validatePassword("abc123").isSuccess());
	}
	
	@Test
	public void testNumbersFollowedByLowercaseLetters() {
		Assert.assertTrue(rule.validatePassword("123abc").isSuccess());
	}
	
	@Test
	public void testMixOfLowercaseLettersAndNumbers() {
		Assert.assertTrue(rule.validatePassword("a1b2c3").isSuccess());
	}
	
	@Test
	public void testMixOfLowercaseLettersAndNumbers2() {
		Assert.assertTrue(rule.validatePassword("123abc4").isSuccess());
	}
}
	