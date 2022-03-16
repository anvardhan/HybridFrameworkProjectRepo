package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.base.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class RediffLoginPage extends BrowserDriverFactory {
	
	
	WebElementUtil selenium = new WebElementUtil(driver);
	
	
	//if passing driver instance from Test class
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public RediffLoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By signInBy = By.xpath("//*[text()='Sign in']");
	public static By userNameBy = By.id("login1");
	public static By passwordBy = By.name("passwd");
	public static By goBy = By.name("proceed");
	public static By homeLinkBy = By.linkText("proceed");
	
	
	//PageFactory - Locators
	@FindBy (xpath = "//*[text()='Sign in']") 
	public WebElement signIn;
	
	@FindBy (id="login1") 
	public WebElement userName;	
	
	@FindBy (name="passwd") 
	public WebElement password;	
	
	@FindBy (name="proceed") 
	public WebElement go;
	
	@FindBy (linkText="Home") 
	public WebElement homeLink;
	
	//Methods/Actions related to LoginPage
	public void loginToApplication(String uname, String psswd) throws Exception
	{
		selenium.click(signIn, "SignInLink");
		selenium.enterText(userName, uname, "LoginTextBox");
		selenium.enterText(password, psswd, "PasswordTextBox");
		selenium.click(go, "SignInBtn");
		
		
		/*
		signIn.click();
		userName.sendKeys(uname);
		password.sendKeys(psswd);
		go.click();	*/	
	}
		

}
