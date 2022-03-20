package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.DriverFactory.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class FinologyLoginPage extends BrowserDriverFactory {
	
	
	WebElementUtil seleniumMethod = new WebElementUtil(driver);
	
	
	//if passing driver instance from Test class
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public FinologyLoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By signInLink= By.xpath("//a[text()='Sign in']");
	public static By mobileTextbox = By.xpath("//input[@name='mobile']");
	public static By nextBtn = By.xpath("//button[@id='btnNext']");
	public static By passwordTextbox = By.xpath("//input[@name='password']");
	public static By passwordTextbox1 = By.xpath("//input[@id='txtPassword']");
	public static By loginBtn = By.xpath("//button[@id='btnLogin']");
	public static By welcomeMsg = By.xpath("//*[contains(text(),'Hello')]");
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
	public void TestFeatureBranch () {
		System.out.println("Added code into master branch by other developer");
		System.out.println("Added code into from feature branch by me");
		System.out.println("Added code into from feature branch by me new");
		System.out.println("Added code into from feature branch by me new2");
	}
		

}
