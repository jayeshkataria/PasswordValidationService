package com.jk.passwordvalidationservice.rules;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jk.passwordvalidationservice.App;
import com.jk.passwordvalidationservice.exceptions.ValidationException;

public class NoRepeatingCharacterSequencesTest {

	
	private static NoRepeatingCharacterSequences rule;
	
	public NoRepeatingCharacterSequencesTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(App.SPRING_CONFIG_PATH)){
			rule = (NoRepeatingCharacterSequences) context.getBean("NoRepeatingCharacterSequencesTest");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test(expected=ValidationException.class)
	public void testValidationException(){
		rule.validatePassword(null);
	}
	
	@Test
	public void testEmptyString() { 
		Assert.assertTrue(rule.validatePassword("").isSuccess());
	}

	@Test
	public void testRepeatingEmptySpaces() { 
		Assert.assertFalse(rule.validatePassword("  ").isSuccess());
	}
	
	@Test
	public void testNonRepeatingEmptySpaces() { 
		Assert.assertTrue(rule.validatePassword(" ! ").isSuccess());
	}
	
	@Test
	public void testNonRepeating01() { 
		Assert.assertTrue(rule.validatePassword("abcd").isSuccess());
	}
	
	@Test
	public void testNonRepeating02() { 
		Assert.assertTrue(rule.validatePassword("a12312a").isSuccess());
	}
	
	@Test
	public void testNonRepeating03() { 
		Assert.assertTrue(rule.validatePassword("12a12d12").isSuccess());
	}

	@Test
	public void testNonRepeating04() { 
		Assert.assertTrue(rule.validatePassword("a123d").isSuccess());
	}
	
	@Test
	public void testRepeating01() { 
		Assert.assertFalse(rule.validatePassword("abbcd").isSuccess());
	}
	
	@Test
	public void testRepeating02() { 
		Assert.assertFalse(rule.validatePassword("abcdd").isSuccess());
	}
	
	@Test
	public void testRepeating03() { 
		Assert.assertFalse(rule.validatePassword("aabcd").isSuccess());
	}
	
	@Test
	public void testRepeating04() { 
		Assert.assertFalse(rule.validatePassword("abcbcd").isSuccess());
	}
	
	@Test
	public void testRepeating05() { 
		Assert.assertFalse(rule.validatePassword("a123123d").isSuccess());
	}
		
	@Test
	public void testRepeating06() { 
		Assert.assertFalse(rule.validatePassword("1234567812345678").isSuccess());
	}
	
	@Test
	public void testRepeating07() { 
		Assert.assertFalse(rule.validatePassword("1234567!iwedshsdaflgsdkfsdifhsfsdcsg88").isSuccess());
	}
	
	@Test
	public void testRepeating08() { 
		Assert.assertFalse(rule.validatePassword("abcabcdefdef").isSuccess());
	}
}