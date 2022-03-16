package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.pages.FinologyLoginPage;
import com.qa.pages.FinologySignupPage;

public class FinologySignupTest extends BaseTest {
	
	
	//SignupPage
	@Test (enabled=false)
	public void finologySignupTest() throws Exception  {		
		
		//Validate that OTP msg (Enter OTP recieved on your Mobile) displayed 
		//when providing unique user details with valid email having @ and valid mobile having 10 digit number
		
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologySignupPage.signUpLink, "Signup link");
		selenium.enterText_By(FinologySignupPage.name, "Ajay Kumar", "Name");
		selenium.enterText_By(FinologySignupPage.email, "ajaykumar122312@gmail.com", "Email");
		selenium.enterText_By(FinologySignupPage.mobile, "1509522588", "Mobile");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologySignupPage.otpMsg, 30, "PRESENCE");
		String otpMsg = selenium.getElementText_By(FinologySignupPage.otpMsg, "OTPMsg");
		
		if(otpMsg.contains("Enter OTP")) {
			System.out.println("Pass - Enter OTP Msg displayed as expected");
		} else {
			System.out.println("Fail - Enter OTP Msg NOT displayed as expected");
		}	
		
	}	
	
	@Test (enabled=false)
	public void finologySignup_ExistingUserEntry_Test() throws Exception  {		
		
		//Validate that User already exist msg displayed displayed 
		//when providing existing user details with valid email having @ and valid mobile having 10 digit number
		
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologySignupPage.signUpLink, "Signup link");
		selenium.enterText_By(FinologySignupPage.name, "Anand Vardhan", "Name");
		selenium.enterText_By(FinologySignupPage.email, "anvardhan551@gmail.com", "Email");
		selenium.enterText_By(FinologySignupPage.mobile, "7799096333", "Mobile");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologySignupPage.errorHead, 30, "PRESENCE");
		
		String errorHead = selenium.getElementText_By(FinologySignupPage.errorHead, "ErrorHead");
		String errorMsg = selenium.getElementText_By(FinologySignupPage.existingUserError, "ErrorMsg");
		
		if(errorHead.equalsIgnoreCase("Error") && errorMsg.equalsIgnoreCase("You already have an account. Please Login to continue.")) {
			System.out.println("Pass - Registration Failed with correct error message which is as expected");
		} else {
			System.out.println("Fail - Registration Failed with incorrect error Message which is Not as expected");
		}	
		
		selenium.click_By(FinologyLoginPage.errorOK, "OKbtn");
		
	}


}
