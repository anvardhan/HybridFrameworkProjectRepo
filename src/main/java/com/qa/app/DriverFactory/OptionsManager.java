package com.qa.app.DriverFactory;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	//private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	Properties prop = null;

	/*public OptionsManager() {
		this.prop = prop;
	}*/

	public ChromeOptions getChromeOptions() throws IOException {
		co = new ChromeOptions();
		//co.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		//co.addArguments("--no-sandbox"); // Bypass OS security model		
//		if(Boolean.parseBoolean("remote")) {
//			co.setPlatformName("Linux");
//			co.setBrowserVersion("94");//			
//		}
		
		prop = BrowserDriverFactory.readConfig();
		
		co.addArguments("--start-maximized");
		co.addArguments("enable-automation");
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
			co.addArguments("window-size=1400,800");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		
		return co;
	}

	public FirefoxOptions getFirefoxOptions() throws IOException {
		fo = new FirefoxOptions();
		
		prop = BrowserDriverFactory.readConfig();
		
		//FirefoxBinary forefoxBinary = new FirefoxBinary();
		//if (Boolean.parseBoolean(prop.getProperty("headless"))) 
			//forefoxBinary.addCommandLineOptions("--headless");
		
		if (Boolean.parseBoolean(prop.getProperty("headless"))) 
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) 
			fo.addArguments("--incognito");
		
		//fo.setBinary(forefoxBinary);
		
		return fo;
	}

}
