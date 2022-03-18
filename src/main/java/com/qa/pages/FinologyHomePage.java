package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.base.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class FinologyHomePage extends BrowserDriverFactory {
	
	
	WebElementUtil seleniumMethod = new WebElementUtil(driver);
	
	
	//if passing driver instance from Test class and use page factory
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public FinologyHomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By productTab = By.xpath("//*[text()='Products']");
	public static By tickerApp = By.xpath("//span[text()='Ticker']");
	public static By selectApp = By.xpath("//span[contains(text(),'Select')]");
	public static By questApp = By.xpath("//span[contains(text(),'Quest')]");
	public static By recipeApp = By.xpath("//span[contains(text(),'Recipe')]");
	
	
	
	//PageFactory - Locators - Yet to implement
	/*@FindBy (xpath = "//*[text()='Products']") 
	public WebElement productTabElement; */
	
	
	
	//Methods/Actions related to HomePage
	
	public void TestFeatureBranch () {
		System.out.println("Added code from feature branch");
		System.out.println("Added additional code from feature branch");
	}
		

}
