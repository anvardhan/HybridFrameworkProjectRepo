package com.qa.app.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriverFactory {
		
	//System.getProperty("user.dir") -> Gives project path
	
	public static WebDriver driver = null;
	public static Properties prop = null;
	
	
	public WebDriver initializeBrowser() throws IOException {
		
		prop=BrowserDriverFactory.readConfig();
		String browser = prop.getProperty("browser");	
	
		Boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
				
		switch(browser) {	
		
			case "chrome": 
				//System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\browserdrivers\\chromedriver.exe");	
				WebDriverManager.chromedriver().setup();
				
				
				if(headless)
				{
					driver = new ChromeDriver();
					
				} else {
					//headless
					ChromeOptions options = new ChromeOptions();				
					options.addArguments("window-size=1400,800");
					options.addArguments("headless");				
					driver = new ChromeDriver(options);
				}
				
				
				/*System.setProperty("webdriver.chrome.verboseLogging", "false");
				ChromeOptions options = new ChromeOptions();
				//System.out.println("aa2");
				// Maximize the browser
				options.addArguments("--start-maximized");
				//System.out.println("aa3");
				options.addArguments("enable-automation");
				driver = new ChromeDriver(options);	*/				
				
				System.out.println("Chrome driver launched");				
				break;
				
			case "ie": 
				System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\browserdrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
				/*InternetExplorerOptions ieOptions= new InternetExplorerOptions();
				ieOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, appUrl);
				ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);                                                                
				ieOptions.setCapability("ensureCleanSession", true);
				ieOptions.setCapability("silent", true); 
				driver = new InternetExplorerDriver(ieOptions); */
				
				System.out.println("IE driver launched");
				break;
				
			case "firefox": 
				System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\browserdrivers\\geckodriver.exe");
				//WebDriverManager.firefoxdriver().setup();
				
				if(headless) {
					driver = new FirefoxDriver();
				} else {
					//headless mode
					FirefoxBinary forefoxBinary = new FirefoxBinary();
					forefoxBinary.addCommandLineOptions("--headless");				
					FirefoxOptions fop = new FirefoxOptions();
					fop.setBinary(forefoxBinary);				
					driver = new FirefoxDriver(fop);
				}
				
				
				/*System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Firefox\\Firefox.exe");				
				FirefoxProfile ffprofile= new FirefoxProfile();
				DesiredCapabilities ffCapabilities = DesiredCapabilities.firefox();
				ffCapabilities.setCapability("firefoxProfile", ffprofile);
				ffCapabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
				driver = new FirefoxDriver(ffCapabilities); */
			
				System.out.println("Firefox driver launched");				
				break;	
			
			case "edge":
				System.setProperty("webdriver.edge.driver", ".\\src\\main\\resources\\browserdrivers\\MicrosoftWebDriver.exe");	
				driver = new EdgeDriver();
				System.out.println("edge driver launched");
				break;
			
			default:
				System.out.println("Incorrect browser provided: "+browser+" Correct browser name should be - 'chrome', 'firefox', 'edge', 'ie'");
				System.exit(0);
				
		}
		
		// Delete all cookies
		if (!(browser.equalsIgnoreCase("Edge"))){
			driver.manage().deleteAllCookies();
		}
		
		
		
		//driver.manage().window().maximize(); //Comment in case of headless run
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		return driver;		
	}
	
	
	public void launchApp() throws IOException {
		
		//Open Application in browser
		
		prop=BrowserDriverFactory.readConfig();
		String appUrl = prop.getProperty("AppUrl");
		
		driver.get(appUrl);
		
		
	}
	
	
	public static Properties readConfig() throws IOException {
		
		File f = new File(".\\src\\test\\resources\\config\\qa.config.properties");		
		FileInputStream fis = new FileInputStream(f);		
		prop = new Properties();		
		prop.load(fis);		
		fis.close();		
		return prop;	
		
	}
	
	
	/**
	 * Closes the browser which is used for running the automation script
	 * @Name closeBrowser  
	 * @applicableTo  Desktop,Mobile   
	 * @description Closes the browser which is used for running the automation script                            
	 */
	public void closeBrowser() {
		driver.close();
	}
	
	/**
	 * Closes All the browser which is used for running the automation script
	 * @Name quitBrowser  
	 * @applicableTo  Desktop,Mobile   
	 * @description Closes All the browser which is used for running the automation script                            
	 */
	public void quitBrowser() {
		driver.quit();
	}
	
	/**
	 * Closes all the existing opened browsers in the system
	 * @Name closeAllBrowser  
	 * @applicableTo  Desktop,Mobile   
	 * @description Closes all the existing opened browsers in the system                    
	 */
	public void closeAllBrowser()
	{
		try
		{	
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("cmd /c taskkill /F /IM chrome.exe");					
		}

		catch(Exception e)
		{

		}
	}
	
	

}
