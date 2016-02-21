package com.jk.passwordvalidationservice.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PasswordValidatorTest {

	private static PasswordValidator val = null;

	private final static Logger LOGGER = Logger.getLogger(PasswordValidatorTest.class.getName());
	
	public PasswordValidatorTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml")){
			val = (PasswordValidator) context.getBean("PasswordValidatorTest");
		}
		
		LOGGER.setLevel(Level.OFF);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		val = null;
	}

	@Test
	public void testCleanPassword() {
		ValidationResult r = val.validatePassword("123abc4");
		Assert.assertTrue(r.isSuccess());
	}
	
	@Test
	public void testAlphanumericCombo(){
		ValidationResult r = val.validatePassword("aA7g5S#D$F$!Ibd3d3g");
		LOGGER.info(r.getValidationMessage());
		Assert.assertFalse(r.isSuccess());
	}
	
	@Test
	public void testNumbersOnly(){
		ValidationResult r = val.validatePassword("3457939354342");
		LOGGER.info(r.getValidationMessage());
		Assert.assertFalse(r.isSuccess());
	}
}
