package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.pages.RediffLoginPage;

public class RediffLoginPageTest extends BaseTest {
		
	RediffLoginPage loginP;
	
	@Test(enabled = false) 
	public void validateLoginTest_1_PF() throws Exception {
		loginP = new RediffLoginPage();
		
		loginP.loginToApplication("anand100", "password1234");
		
		System.out.println("validateLoginTest is completed");
	
	}
	
	@Test(enabled = true) 
	public void validateLoginTest_2_PF() throws Exception {
		
		selenium.click(loginP.signIn, "SignInLink");
		selenium.enterText(loginP.userName, "anand100", "UserNameTextBpx");
		selenium.enterText(loginP.password, "password1234", "PasswordTextBpx");
		selenium.click(loginP.go, "GoBtn");
				
		System.out.println("validateLoginTest2 is completed");
	}
	
	@Test(enabled = true) 
	public void validateLoginTest_3_PO() throws Exception {
		
		//System.out.println(selenium.getTitleByJS());
		//System.out.println(selenium.getInnerTextByJS());
		
		selenium.explicitWait_By(RediffLoginPage.signInBy, 20, "PRESENCE");
		selenium.highlightElement(loginP.signIn);
		selenium.click_By(RediffLoginPage.signInBy, "SignInLink");
		
		selenium.explicitWait_By(RediffLoginPage.userNameBy, 20, "VISIBILITY");
		//selenium.enterText_By(AppLoginPage.userNameBy, "anand100", "UserNameTextBpx");
		//selenium.enterTextById("login1", "anand100"); //login1 is id of user name textbox
		selenium.enterTextByJS(loginP.userName, "anand100");
		
		selenium.enterText_By(RediffLoginPage.passwordBy, "password1234", "PasswordTextBpx");
		selenium.drawBorderOnElement(loginP.password);
		//selenium.click_By(AppLoginPage.goBy, "GoBtn");
		selenium.clickElementByJS(loginP.go);
				
		System.out.println("validateLoginTest3 is completed");
		
		selenium.refreshBrowserByJs();
		
		//Thread.sleep(10000);
		
		//seleniumMethod.generateAlert("validateLoginTest3 is completed!!!");		
		//seleniumMethod.acceptAlert();
	}
	
	

}
