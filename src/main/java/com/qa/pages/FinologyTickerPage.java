package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.app.base.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class FinologyTickerPage extends BrowserDriverFactory {
	
	
	WebElementUtil selenium = new WebElementUtil(driver);
	
	//if passing driver instance from Test class
	/*WebDriver driver;
	public AppLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}*/
	
	//Using driver instance from TestBase class
	public FinologyTickerPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//PageByObjects- Locators	
	public static By tickerImgLogo= By.xpath("//*[@id='dnav']/a/img");	
	public static By tickerSearchbox = By.xpath("//input[@id='txtSearchComp']");		
	public static By tickerSearchSuggestions = By.xpath("//div[@class='form-group mt-3']/div//a");		
	public static By stockNameNxtPage = By.xpath("//span[@id='mainContent_ltrlCompName']");	
	public static By sectorTab = By.xpath("//div[@id='dnav']//div[1]//li/a[text()='Sector']");
	public static By sectorPrivateBank = By.xpath("//*[@id='mainContent_sector']//h4[contains(text(),'Bank - Private')]/following-sibling::div/a");	
	public static By sectorListedCompanies = By.xpath("//*[@id='companylist']/tbody/tr/td[2]");
	
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
