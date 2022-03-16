package com.qa.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.FinologyLoginPage;

public class FinologyLoginTest extends BaseTest {
		
	//LoginPage
	@Parameters({"mobile","password"})
	@Test (enabled=true, priority = 1, groups = {"sanity", "mandatory", "regresiion"})
	public void finologyLoginByPhoneTest(String userIdMobile, String password) throws Exception  {		
		
		/*driver.findElement(By.xpath("//a[text()='Sign in']")).click();		
		//enter mobile 7799096333 and password Pnbe@305		
		driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys("7799096333");
		driver.findElement(By.xpath("//button[@id='btnNext']")).click();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pnbe@305");
		driver.findElement(By.xpath("//button[@id='btnLogin']")).click();
		Thread.sleep(3000);		
		String welcomeMsg = driver.findElement(By.xpath("//*[contains(text(),'Hello')]")).getText();*/
		selenium.explicitWait_By(FinologyLoginPage.signInLink, 30, "CLICKABLE");
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.explicitWait_By(FinologyLoginPage.mobileTextbox, 30, "PRESENCE");
		selenium.enterText_By(FinologyLoginPage.mobileTextbox, userIdMobile, "PhoneNo");
		selenium.click_By(FinologyLoginPage.nextBtn, "Continue btn");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, password, "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologyLoginPage.welcomeMsg, 30, "PRESENCE");
		String welcomeMsg = selenium.getElementText_By(FinologyLoginPage.welcomeMsg, "WelcomeText");
		
		if(welcomeMsg.contains("Hello")) {
			System.out.println("Pass - Successful Login by phone");
			Assert.assertTrue(true);
		} else {
			System.out.println("Fail - Login Failed");
			Assert.assertFalse(false);
		}	
		
		
		
		driver.findElement(By.xpath("//*[@id='userphoto']")).click();
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		
		//Assert.assertTrue(false);
		
	}
	
	@Test (enabled=true, priority=2, groups = {"sanity", "mandatory"}, dataProvider="credentials")
	public void finologyLoginByEmail_EmailTest(String userIdEmailMobile, String password, String iterationNo) throws Exception  {		
		
		Reporter.log("This is" +iterationNo+ "of this test");
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologyLoginPage.loginWithEmailLink, "LoginWithEmailLink");
		selenium.enterText_By(FinologyLoginPage.emailOrPhoneTextbox, userIdEmailMobile, "Email");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, password, "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologyLoginPage.welcomeMsg, 30, "PRESENCE");
		String welcomeMsg = selenium.getElementText_By(FinologyLoginPage.welcomeMsg, "WelcomeText");
				
		if(welcomeMsg.contains("Hello")) {
			System.out.println("Pass - Successful Login by email using email");
			Assert.assertTrue(true);
		} else {
			System.out.println("Fail - Login Failed");
			Assert.assertFalse(false);
		}	
		
		driver.findElement(By.xpath("//*[@id='userphoto']")).click();
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
				
	}
	
	@Test (enabled=true, priority=2, groups = {"sanity", "mandatory"}, dataProvider="credentials")
	public void finologyLoginByEmail_EmailDuplicateTest(String userIdEmailMobile, String password, String iterationNo) throws Exception  {		
		
		Reporter.log("This is" +iterationNo+ "of this test");
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologyLoginPage.loginWithEmailLink, "LoginWithEmailLink");
		selenium.enterText_By(FinologyLoginPage.emailOrPhoneTextbox, userIdEmailMobile, "Email");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, password, "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologyLoginPage.welcomeMsg, 30, "PRESENCE");
		String welcomeMsg = selenium.getElementText_By(FinologyLoginPage.welcomeMsg, "WelcomeText");
				
		if(welcomeMsg.contains("Hello")) {
			System.out.println("Pass - Successful Login by email using email");
			Assert.assertTrue(true);
		} else {
			System.out.println("Fail - Login Failed");
			Assert.assertFalse(false);
		}	
		
		driver.findElement(By.xpath("//*[@id='userphoto']")).click();
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
				
	}
	
	@Test (enabled=false)
	public void finologyLoginByEmail_PhoneTest() throws Exception  {		
					
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologyLoginPage.loginWithEmailLink, "LoginWithEmailLink");
		selenium.enterText_By(FinologyLoginPage.emailOrPhoneTextbox, "7799096333", "Email");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, "Pnbe@305", "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		selenium.explicitWait_By(FinologyLoginPage.welcomeMsg, 30, "PRESENCE");
		String welcomeMsg = selenium.getElementText_By(FinologyLoginPage.welcomeMsg, "WelcomeText");
				
		if(welcomeMsg.contains("Hello")) {
			System.out.println("Pass - Successful Login by email using phone");
		} else {
			System.out.println("Fail - Login Failed");
		}	
		
		driver.findElement(By.xpath("//*[@id='userphoto']")).click();
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
				
	}
	
	@Test (enabled=false)
	public void finologyLoginByPhoneTest_InvalidPassword() throws Exception  {		
		//Provide Valid Phone number which is registerd in the App
		//Provide invalid password
		//Expected - ERROR You entered wrong details. Please re-check and try again.
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.enterText_By(FinologyLoginPage.mobileTextbox, "7799096333", "PhoneNo");
		selenium.click_By(FinologyLoginPage.nextBtn, "Continue btn");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, "Pnbe@304", "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
		
		//p[@class='heading'] - Error
		//p[@class='msg'] - You entered wrong details. Please re-check and try again.
		
		String errorHeading = selenium.getElementText_By(FinologyLoginPage.errorHeadingMsg, "ErrorHead");
		String errorMessage = selenium.getElementText_By(FinologyLoginPage.errorMsg, "ErrorMsg");
				
		if(errorHeading.equalsIgnoreCase("Error") && errorMessage.equalsIgnoreCase("You entered wrong details. Please re-check and try again.")) {
			System.out.println("Pass - Login Failed with correct error message which is as expected");
		} else {
			System.out.println("Fail - Login Failed with incorrect error Message which is Not as expected");
		}	
		
		
		selenium.click_By(FinologyLoginPage.errorOK, "OKbtn");
		
		//driver.findElement(By.xpath("//*[@id='userphoto']")).click();
		//driver.findElement(By.xpath("//span[text()='Logout']")).click();
		
	}
	
	@Test (enabled=false)
	public void finologyLoginByPhoneTest_InvalidPhoneNo() throws Exception  {		
		//Provide InValid Phone no (less than or greater than 10 digit Phone number)
		//Provide valid/Invalid password
		//Expected - user should be taken to the register page
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.enterText_By(FinologyLoginPage.mobileTextbox, "5245845", "PhoneNo");
		selenium.click_By(FinologyLoginPage.nextBtn, "Continue btn");
		
		String mobileErrorTxt = selenium.getElementText_By(FinologyLoginPage.mobileErrorMsg, "MobileErrorTxt");
			
		if(mobileErrorTxt.contains("Invalid Mobile")) {
			System.out.println("Pass - Required error msg is displaying as expected");
		} else {
			System.out.println("Fail - incorrect error msg displayed");
		}	
		
	}
	
	@Test (enabled=true, dataProvider="invalidCredentials")
	public void finologyLoginByEmail_InvalidEmail__EmailTest(String email, String password, String iterationNo) throws Exception  {		
		
		System.out.println("Iteration No: for finologyLoginByEmail_InvalidEmail: "+iterationNo);
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologyLoginPage.loginWithEmailLink, "LoginWithEmailLink");
		selenium.enterText_By(FinologyLoginPage.emailOrPhoneTextbox, email, "Email");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, password, "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
				
		String errorHeading = selenium.getElementText_By(FinologyLoginPage.errorHeadingMsg, "ErrorHead");
		String errorMessage = selenium.getElementText_By(FinologyLoginPage.errorMsg, "ErrorMsg");
				
		if(errorHeading.equalsIgnoreCase("Error") && errorMessage.equalsIgnoreCase("You entered wrong details. Please re-check and try again.")) {
			System.out.println("Pass - Login Failed with correct error message which is as expected");
		} else {
			System.out.println("Fail - Login Failed with incorrect error Message which is Not as expected");
		}	
		
		selenium.click_By(FinologyLoginPage.errorOK, "OKbtn");
					
	}	
	
	@Test (enabled=false)
	public void finologyLoginByEmail_InvalidPhone__EmailTest() throws Exception  {		
						
		selenium.click_By(FinologyLoginPage.signInLink, "SignIn Link");
		selenium.click_By(FinologyLoginPage.loginWithEmailLink, "LoginWithEmailLink");
		selenium.enterText_By(FinologyLoginPage.emailOrPhoneTextbox, "7799196333", "Email");
		selenium.enterText_By(FinologyLoginPage.passwordTextbox, "Pnbe@305", "Password");
		selenium.click_By(FinologyLoginPage.loginBtn, "Login btn");
				
		String errorHeading = selenium.getElementText_By(FinologyLoginPage.errorHeadingMsg, "ErrorHead");
		String errorMessage = selenium.getElementText_By(FinologyLoginPage.errorMsg, "ErrorMsg");
				
		if(errorHeading.equalsIgnoreCase("Error") && errorMessage.equalsIgnoreCase("You entered wrong details. Please re-check and try again.")) {
			System.out.println("Pass - Login Failed with correct error message which is as expected");
		} else {
			System.out.println("Fail - Login Failed with incorrect error Message which is Not as expected");
		}	
		
		selenium.click_By(FinologyLoginPage.errorOK, "OKbtn");
					
	}
	

	@DataProvider (name = "credentials")
	public Object[][] validUserCredential_dataprovider() {
		
		Object[][] userData = {
								{"anvardhan551@gmail.com", "Pnbe@305", "iteration1"}, 
								{"7799096333","Pnbe@305", "iteration2"},
								{"anvardhan551@gmail.com", "Pnbe@305", "iteration1"}
							  };
		
		return userData;				
		
	}
	
	@DataProvider (name = "invalidCredentials")
	public Object[][] InvalidEmailUserCredential_dataprovider() {
		
		Object[][] userData = {
								{"anvardhan552@gmail.com", "Pnbe@305", "iteration1"}, 
								{"test12","Pnbe@305", "iteration2"},
								{"anvardhan551gmail.com", "Pnbe@305", "iteration1"}
							  };
		
		return userData;				
		
	}
	

}
