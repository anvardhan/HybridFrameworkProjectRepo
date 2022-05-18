package com.qa.app.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriverFactory {
		
	//System.getProperty("user.dir") -> Gives project path
	
	public static WebDriver driver = null;
	public static Properties prop = null;
	public OptionsManager optionsManager = null;
	
	public WebDriver initializeBrowser() throws IOException {
		
		prop=BrowserDriverFactory.readConfig();
		String browserName = prop.getProperty("browser");	
		optionsManager = new OptionsManager();
				
		switch(browserName) {	
		
			case "chrome": 
				System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\browserdrivers\\chromedriver.exe");	
				//WebDriverManager.chromedriver().setup();					
				driver = new ChromeDriver(optionsManager.getChromeOptions());				
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
				driver = new FirefoxDriver(optionsManager.getFirefoxOptions());				
				System.out.println("Firefox driver launched");				
				break;	
			
			case "edge":
				System.setProperty("webdriver.edge.driver", ".\\src\\main\\resources\\browserdrivers\\MicrosoftWebDriver.exe");	
				driver = new EdgeDriver();
				System.out.println("edge driver launched");
				break;
			
			default:
				System.out.println("Incorrect browser provided: "+browserName+" Correct browser name should be - 'chrome', 'firefox', 'edge', 'ie'");
				System.exit(0);
				
		}
		
		// Delete all cookies
		
		driver.manage().deleteAllCookies();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		return driver;		
	}
	
	
	
	/*private void init_remoteDriver(String browser, String browserVersion) {

		System.out.println("Running test on remote grid server: " + browser);
		if (browser.equalsIgnoreCase("chrome")) {
			//selenium 3.x
			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setBrowserName("chrome");
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);			
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setAcceptInsecureCerts(true);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}*/
	
	
	
	public void launchApp() throws IOException {
		
		//Open Application in browser
		
		prop=BrowserDriverFactory.readConfig();
		String appUrl = prop.getProperty("AppUrl");
		
		driver.get(appUrl);
		
		
	}
	
	
	public static Properties readConfig() throws IOException {
		
	/*	File f = new File(".\\src\\test\\resources\\config\\qa.config.properties");		
		FileInputStream fis = new FileInputStream(f);		
		prop = new Properties();		
		prop.load(fis);		
		fis.close();		
		return prop; */	
		
		//Running on different environments through Maven/Jenkins - use environments as needed
		//mvn clean install -Denv="dev"
		
		prop = new Properties();
		FileInputStream fis = null;

		String envName = System.getProperty("env");// qa/dev/stage/uat

		if (envName == null) {
			System.out.println("Running on QA env: ");
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + envName);
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					fis = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "dev":
					fis = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "stage":
					fis = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "uat":
					fis = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;

				default:
					System.out.println("please pass the right environment.....");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
