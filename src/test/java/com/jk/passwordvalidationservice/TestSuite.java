package com.jk.passwordvalidationservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jk.passwordvalidationservice.rules.AtleastOneNumberAndLowerCaseLetterTest;
import com.jk.passwordvalidationservice.rules.LengthBetweenRangeInclusiveTest;
import com.jk.passwordvalidationservice.rules.LowerCaseAndNumbersOnlyTest;
import com.jk.passwordvalidationservice.rules.NoRepeatingCharacterSequencesTest;
import com.jk.passwordvalidationservice.validator.PasswordValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({
	LowerCaseAndNumbersOnlyTest.class,
	AtleastOneNumberAndLowerCaseLetterTest.class,
	LengthBetweenRangeInclusiveTest.class,
	NoRepeatingCharacterSequencesTest.class,
	PasswordValidatorTest.class
})
public class TestSuite {

}
