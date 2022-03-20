package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.DriverFactory.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class FinologySelectPage extends BrowserDriverFactory {
	
	
	WebElementUtil seleniumMethod = new WebElementUtil(driver);
	
	
	//if passing driver instance from Test class
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public FinologySelectPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By selectImgLogo= By.xpath("//a[@class='navbar-brand']/img");	
	public static By brokersTab = By.xpath("//div[@id='mainnav']/ul//a[text()='Brokers']");	
	public static By zerodhaBroker = By.xpath("(//section[contains(@class,'brokerslist')]//h4/a[1])[1]");	
	public static By brokerList = By.xpath("//section[contains(@class,'brokerslist')]//h4/a[1]");	
	public static By allBrokerList = By.xpath("//section[contains(@class,'brokerslist')]//div[@class='col-12 col-md-10']");
	public static By calculatorsTab = By.xpath("//div[@id='mainnav']/ul//a[text()='Calculators']");	
	public static By calculatorsBrokerList = By.xpath("//section[contains(@class,'brokerslist')]//div[@class='card smallcard']");
	public static By calculatorBrokerHeader = By.xpath("//h1");
	public static By launchCalculatorICICIDirect = By.xpath("//section[contains(@class,'brokerslist')]//h4[text()='ICICI Direct']/parent::div/following-sibling::div//a");
	public static By calculatorDeliveryTab =By.xpath("//a[contains(text(),'Delivery')]");
	public static By calculatorPlanDrpDown = By.xpath("//select[@id='planType']");
	public static By calculatorQuantity = By.xpath("//input[@id='quantity']");
	public static By calculatorPrice = By.xpath("//input[@id='price']");
	public static By calculatorNseRadBtn = By.xpath("//p[text()='Exchange']/following-sibling::label[2]");
	public static By calculateBtn = By.xpath("//a[@id='seeBrokerage']");
	public static By brokerageRate = By.xpath("//span[@id='brokerageDe']");
	public static By brokerageCharge = By.xpath("//span[@id='brokerageChargesDe']");
	
	
	//PageFactory - Locators - Yet to implement
	/*@FindBy (xpath = "//*[text()='Products']") 
	public WebElement productTabElement; */
	
	
	
	//Methods/Actions related to LoginPage
	
		

}
