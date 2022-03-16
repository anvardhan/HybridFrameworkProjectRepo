package com.qa.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.qa.app.base.BrowserDriverFactory;
import com.qa.app.utils.WebElementUtil;

public class BaseTest {
	
	static WebDriver driver = null;
	protected BrowserDriverFactory browserDriverFactory = null;
	protected WebElementUtil selenium;
	protected SoftAssert softassert;
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws IOException {
			
		//WebDriverManager.chromedriver().setup();
		/*System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\browserdrivers\\chromedriver.exe");
		driver = new ChromeDriver();				
		driver.get("https://www.finology.in/");		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); */
		
		browserDriverFactory = new BrowserDriverFactory();
		driver = browserDriverFactory.initializeBrowser();
		browserDriverFactory.launchApp();
		selenium = new WebElementUtil(driver);
		softassert = new SoftAssert();
		
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		//driver.close();
		browserDriverFactory.closeBrowser();
		//TestBase.quitBrowser();
	}

}
