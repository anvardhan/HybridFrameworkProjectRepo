package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.DriverFactory.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class FinologySignupPage extends BrowserDriverFactory {
	
	
	WebElementUtil selenium = new WebElementUtil(driver);
	
	
	//if passing driver instance from Test class
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public FinologySignupPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By signUpLink= By.xpath("//a[text()='Sign Up']");
	public static By name = By.xpath("//input[@id='Name']");
	public static By email = By.xpath("//input[@id='Email']");
	public static By mobile = By.xpath("//input[@id='mobile']");
	public static By otpMsg = By.xpath("//label[contains(text(),'Enter OTP recieved on your Mobile')]");	
	public static By errorHead = By.xpath("//p[contains(text(),'ERROR')]");	
	public static By existingUserError = By.xpath("//p[contains(text(),'You already have an account. Please Login to continue')]");
	
	
	public static By loginWithEmailLink = By.xpath("//a[contains(text(),'Login with Email')]");
	public static By emailOrPhoneTextbox =By.xpath("//input[@id='txtEmailMobile']");
	public static By errorHeadingMsg = By.xpath("//p[@class='heading']");
	public static By errorMsg = By.xpath("//p[@class='msg']");
	public static By errorOK = By.xpath("//a[contains(text(),'Ok')]");
	public static By mobileErrorMsg = By.xpath("//span[@id='mobile-error']");
	
	
	//PageFactory - Locators - Yet to implement
	/*@FindBy (xpath = "//*[text()='Products']") 
	public WebElement productTabElement; */
	
	
	
	//Methods/Actions related to LoginPage
	
		

}
