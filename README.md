# Password Validation Service

## Problem Statement
Write a password validation service in Java, meant to be configurable via IoC using the Spring Framework. The service is meant to check a text string for compliance to any number of password validation rules.
The rules currently known are:

* Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
* Must be between 5 and 12 characters in length.
* Must not contain any sequence of characters immediately followed by the same sequence.

## Solution
**A. Build and Run**

The project can be built as follows:

1. From the command line, cd into the PasswordValidationService folder. You should see a pom.xml file and a src folder

2. Run the following maven command:
    ```
    mvn clean install
    ```
	This will download any dependencies and run all the tests and on success it will create a target 	folder with with a runnable jar file named : password-validation-service-0.0.1.jar

3. To run the project you can now run this command from the same location: 
     ```
    java -jar target/password-validation-service-0.0.1.jar
	```

The service has been created using Jetty and you can test the service (on port 12000) by calling the following url: 

http://localhost:12000/passwordvalidator/validate?password=123aAfghighahafh

As you can see in the URL, you can pass the password at the end of the URL and the service should return success/failure along with failure message(s). For example, the url above should return the following:
```javascript
{"success":false,"validationMessage":"Password can only contain lowercase letters and numbers, Password must be between 5 and 12 characters in length, Password must not contain any repeating sequences of characters, "}
```

**B. Rule Logic**

Based on the requirements, the code is validating 4 separate rules in conjunction as follows:
1. Mixture of lowercase letters and numerical digits only
2. At least one lowercase letter and at least one numerical digit
3. Length between 5 and 12 characters
4. No repeating sequences of characters.

These 4 rules need to be run together on the password to satisfy the validation requirements set forth in the programming exercise.

>You will find that I have split the first rule in the problem statement (mixture of lowercase and numeric ... with at least one of each) into 2 parts. I've done this simply because it led to much easier unit testing and code simplification.

**C. Project Setup, Spring, Testing, etc.**

1. I've developed the project using Eclipse and the repo contains the necessary files to open the project in Eclipse. 


2. As per the requirements, the service has been developed using Spring and XML configuration has been used to configure the rule beans and the List of rules that are run by the service. You can find the configuration in the Beans.xml file at ./src/main/resources/Beans.xml. 

	In there, you will find the Rule List "PasswordValidationDefaultRuleList"  that has the rule list. This is then fed into the "DefaultPasswordValidator" (in the XML itself) which is used eventually by com.jk.passwordvalidationservice.PasswordValidationService to get the rules using Spring and run the validation.

	The beans setup for the rules have also been used for testing using Aliases so that the tests can be changed by simply updating the Beans.xml file in the future. Also, for the password length rule, you will find that this is done in XML as well to make room for future adjustments if needed.

	I've setup the project in this manner based on the requirement and also to ensure that as much of the configuration as possible can be customized by changing beans.xml.


3. The JUnit tests are sitting in ./src/test/java. There are 54 tests in all, across the 4 rules and the Validator. 