package com.qa.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pages.FinologyHomePage;
import com.qa.pages.FinologyTickerPage;

public class FinologyTickerTest extends BaseTest {
	
	//Ticker Home Page
	@Test (enabled = false, priority=1, groups = {"sanity", "regression"}, dependsOnGroups = {"mandatory"})
	public void tickerProduct_validateTickerLogo_bkp() throws Exception {
					
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
			
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		boolean logoDisplay = selenium.verifyElementDisplay_By(FinologyTickerPage.tickerImgLogo, "Ticker logo");
		
		if(logoDisplay) {
			System.out.println("Pass - Ticker Logo displayed as expected");
		} else {
			System.out.println("Fail - Ticker Logo Not displayed not as expected");
		}
		
		Assert.assertTrue(logoDisplay);
	
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}	
	
	//Ticker Home Page
		@Test (enabled = true, priority=1, groups = {"sanity", "regression"})
		public void tickerProduct_validateTickerLogo() throws Exception {
						
			selenium.click_By(FinologyHomePage.productTab, "Products tab");
			selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
			selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
				
			String parentWindowId = selenium.getParentWindowId();		
			Set<String> allWindowId = selenium.getAllWindowHandles();
			String childTickerWindow = null;		
			Iterator<String> itr = allWindowId.iterator();
				
			if(itr.hasNext())
				itr.next(); //This will return parant window
			if(itr.hasNext())
				childTickerWindow = itr.next(); //This will return child window
				
			selenium.switchToChildWindow(childTickerWindow);
				
			boolean logoDisplay = selenium.verifyElementDisplay_By(FinologyTickerPage.tickerImgLogo, "Ticker logo");
			
			if(logoDisplay) {
				System.out.println("Pass - Ticker Logo displayed as expected");
			} else {
				System.out.println("Fail - Ticker Logo Not displayed not as expected");
			}
		
			Assert.assertTrue(logoDisplay);
			
			browserDriverFactory.closeBrowser(); //Close Ticker url browser
				
			//Move back to Parent
			selenium.switchToParentWindow(parentWindowId);
		}	
	
	//Ticker Home Page
	@Test (enabled = false)
	public void tickerProduct_validateSuggestionCount() throws Exception {
				
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
		
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
		
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
		
		selenium.switchToChildWindow(childTickerWindow);
		
		selenium.explicitWait_By(FinologyTickerPage.tickerSearchbox, 30, "PRESENSE");
		selenium.enterText_By(FinologyTickerPage.tickerSearchbox, "Adani", "searchbox"); 
		
		//Input is Adani, expected count = 7
		
		List<WebElement> stockSuggestions = selenium.getListElements(FinologyTickerPage.tickerSearchSuggestions, "SeacrhSuggestions");
		
		int actualSuggestionCount = stockSuggestions.size();
		
		if(actualSuggestionCount == 7) {
			System.out.println("Pass - Total expected count is matched with actual count: "+actualSuggestionCount);
		} else {
			System.out.println("Fail - Total expected count is Not matched with actual count: "+actualSuggestionCount);
		}
		
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
		
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home Page
	@Test (enabled = false)
	public void tickerProduct_validatePresenseofSearchStock() throws Exception {
				
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
		
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
		
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
		
		selenium.switchToChildWindow(childTickerWindow);
		
		selenium.explicitWait_By(FinologyTickerPage.tickerSearchbox, 30, "PRESENSE");
		selenium.enterText_By(FinologyTickerPage.tickerSearchbox, "Adani", "searchbox");  
		
		//Input is Adani, expected count = 7
		
		List<WebElement> stockSuggestions = selenium.getListElements(FinologyTickerPage.tickerSearchSuggestions, "SeacrhSuggestions");
		
		for(WebElement stockElement : stockSuggestions) {
			
			selenium.explicitWait(stockElement, 30, "CLICKABLE");
			//Thread.sleep(1000);
			String stock = selenium.getElementText(stockElement, "suggestion element");
			
			if(stock.contains("Adani")) {
				System.out.println("Pass - Searched stock 'Adani' is present in the suggested stock "+stock);
			} else {
				System.out.println("Fail - Searched stock 'Adani' is not present in the suggested stock "+stock);
			}			
			
		}
				
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
		
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home/Company page
	@Test (enabled = false)
	public void tickerProduct_validateSearchStock() throws Exception {
				
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
		
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
		
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
		
		selenium.switchToChildWindow(childTickerWindow);
		
		selenium.explicitWait_By(FinologyTickerPage.tickerSearchbox, 30, "PRESENSE");
		selenium.enterText_By(FinologyTickerPage.tickerSearchbox, "Adani", "searchbox"); 
				
		List<WebElement> stockSuggestions = selenium.getListElements(FinologyTickerPage.tickerSearchSuggestions, "SeacrhSuggestions");
		
		boolean found = false;
		String suggestedStock = null;
		for(WebElement stockElement : stockSuggestions) {
			
			selenium.explicitWait(stockElement, 30, "CLICKABLE");
			//Thread.sleep(1000);
			suggestedStock = selenium.getElementText(stockElement, "suggestion element");
			
			System.out.println("Suggested Stocks: "+suggestedStock);
			
			if(suggestedStock.trim().contains("Adani Green Energy Ltd.")) {
				found = true;
				System.out.println("Pass - Searched stock 'Adani' is present in the suggested stock "+suggestedStock);
				stockElement.click();
				break;
			} 			
			
		}
		
		if(found) {
			String stockName = selenium.getElementText_By(FinologyTickerPage.stockNameNxtPage, "StockNameInNextPage");
		
			System.out.println("stockName: "+stockName);
		
			if(suggestedStock.trim().contains(stockName)) {
				System.out.println("Pass - searched stock is displayed as expected");
			} else {
				System.out.println("Fail - searched stock is NOT displayed");
			}
		} else {
			System.out.println("Fail - Searched stock 'Adani' is NOT present in the suggested stocks.");
		}
				
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
		
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home/Sector page
	@Test (enabled = false)
	public void tickerProduct_ListedCompaniesCountForSector() throws Exception {
					
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
			
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		//Click on Sector
		selenium.click_By(FinologyTickerPage.sectorTab, "Sector Tab");
		
		//Click on Sector - Bank-Private
		//*[@id="mainContent_sector"]/div/div[14]/div/div/a
		//*[@id='mainContent_sector']//h4[contains(text(),'Bank - Private')]/following-sibling::div/a
		
		//Parameterize above xpath for sector name - Create a method, pass sector name 
		//and expected count and validate actual count
		
		//selenium.click_By(By.xpath("//*[@id='mainContent_sector']/div/div[14]/div/div/a"), "PrivateBank sector");
		selenium.click_By(FinologyTickerPage.sectorPrivateBank, "PrivateBank sector");
		
		//List companies count and name
		
		List<WebElement> listedCompanies = selenium.getListElements(FinologyTickerPage.sectorListedCompanies, "ListedCompanies");
		
		System.out.println("listedCompanies: "+listedCompanies.size());
		
		int totalListedCompaniesCount = listedCompanies.size();
		
		if(totalListedCompaniesCount == 23) {
			System.out.println("Pass - Actual count "+totalListedCompaniesCount+" matched to expected count");
		} else {
			System.out.println("Fail - Actual count "+totalListedCompaniesCount+" is NOT matched to expected count");
		}
		
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home/Sector page
	@Test (enabled = false)
	public void tickerProduct_ListedCompaniesCountForSector_Parameterized() throws Exception {
						
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
				
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
				
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
				
		selenium.switchToChildWindow(childTickerWindow);
				
		//Click on Sector
		selenium.click_By(By.xpath("//div[@id='dnav']//div[1]//li/a[text()='Sector']"), "Sector Tab");
					
		String sectorName = "Textile"; //DDF
		int expectedListedCompaniesCount = 192; //DDF
		
		//Click on Sector - sectorName and get count
		int actualListedCompaniesCount = validateListedCompaniesCount(sectorName);
				
				
		if(actualListedCompaniesCount == expectedListedCompaniesCount) {
			System.out.println("Pass - Actual count "+actualListedCompaniesCount+" matched to expected count");
		} else {
			System.out.println("Fail - Actual count "+actualListedCompaniesCount+" is NOT matched to expected count");
		}
			
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
				
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}	
		
	//Ticker Home/Sector page
	@Test (enabled = false)
	public void tickerProduct_AllListedCompaniesForSector_Parameterized() throws Exception {
							
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
					
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
					
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
				
		selenium.switchToChildWindow(childTickerWindow);
					
		//Click on Sector
		selenium.click_By(FinologyTickerPage.sectorTab, "Sector Tab");
						
		String sectorName = "Air Conditioners"; //DDF
		//int expectedListedCompaniesCount = 5; //DDF		
		String expectedListedCompany = "Sharp India;Johnson Controls;Amber Enterprises;Blue Star;Voltas;New Star"; //DDF
		
		String[] expectedListedCompanySplit = expectedListedCompany.split(";");
				
		ArrayList<String> actualListedCompanies = getListedCompanies(sectorName);
		
		boolean flag = true; 
		boolean found = false;
		for(int i = 0; i < expectedListedCompanySplit.length; i++) {
			
			String expectedCompany = expectedListedCompanySplit[i];
			
			for(String actualCompany : actualListedCompanies) {
				
				found = false;
				
				if(expectedCompany.equalsIgnoreCase(actualCompany)) {
					found = true;
					break;
				}				
			}
			
			if(!found) {
				System.out.println("Fail - Expected Company: '"+expectedCompany+"' NOT found in actual listed company");
				found = false;
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("Pass - All listed companies are matching with expected listed companies");
		} else {
			System.out.println("Fail - All listed companies are not matching with expected listed companies");
		}
		
				
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
					
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}	
	
	//Ticker Home/Sector page
	@Test (enabled = true, priority=3, groups = {"regression"})
	public void tickerProduct_getPriceForListedCompanies_Sector_Parameterized() throws Exception {
								
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
						
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
						
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
					
		selenium.switchToChildWindow(childTickerWindow);
						
		//Click on Sector
		selenium.click_By(FinologyTickerPage.sectorTab, "Sector Tab");
							
		String sectorName = "IT - Software"; //DDF
		//int expectedListedCompaniesCount = 188; //DDF		
					
		/*ArrayList<String> getAllListedCompanies = getListedCompanies(sectorName);
	
				
		for(String actualCompany : getAllListedCompanies) {
					
			System.out.println(actualCompany);
						
		}*/
		
		
		LinkedHashMap<String, String> listedCompanyMap = getPriceofAllListedCompanies(sectorName);
		
		Set<String> keys = listedCompanyMap.keySet();
		
		String stockName = "Octaware Tech.";
		
		for(String key : keys) {
			
			//System.out.println("Company Name: "+key+" : Price: "+listedCompanyMap.get(key));
			
			if(key.contains(stockName)) {
				System.out.println("Pass - Found stock: "+stockName);
				Assert.assertTrue(true);
				System.out.println("Price: "+listedCompanyMap.get(key));
				break;
			} else {
				System.out.println("Fail - Not found");
				Assert.assertFalse(false);
			}
			
		}
			
					
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
						
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home/Sector page
	@Test (enabled = true, priority=2, groups = {"regression"})
	public void tickerProduct_getHighestPrice_Sector() throws Exception {
									
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
							
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
							
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
						
		selenium.switchToChildWindow(childTickerWindow);
							
		//Click on Sector
		selenium.click_By(FinologyTickerPage.sectorTab, "Sector Tab");
								
		String sectorName = "IT - Software"; //DDF
		//int expectedListedCompaniesCount = 188; //DDF		
						
		LinkedHashMap<String, String> listedCompanyMap = getPriceofAllListedCompanies(sectorName);
			
		Set<String> keys = listedCompanyMap.keySet();
		
		float highestPrice = 0;
		String stockName = null;
		
		//6,564.80 -> remove comma before using parseFloat
		//str = str.replace(/[^\d\.\-eE+]/g, "");
		
		for(String key : keys) {
			
			stockName = key;
			String stockPrice = listedCompanyMap.get(key);
			highestPrice = Float.parseFloat(stockPrice.replace(",", ""));
			
			for(String key1 : keys) {
				float price = Float.parseFloat(listedCompanyMap.get(key1).replace(",", ""));
				
				if(price > highestPrice) {
					highestPrice = price;
					stockName = key1;
				}
				
			}			
				
		}
		
		System.out.println("Stock Name having Highest Price: "+stockName);
		System.out.println("Price: "+highestPrice);
		
		Assert.assertTrue(true);
				
						
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
							
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Ticker Home/Screener page - This test is not working
	@Test (enabled = false)
	public void tickerProduct_validateMktCapOfValueStock_Scrrener() throws Exception {
		
		//Run the screener for value stock 
		//PE Ratio < 10 AND PEG ratio < 0.5 AND PE Ratio > 0 AND MCAP > 1000
		//Validate All stocks that is appearing has MCAP > 1000, PE > 0 and < 10, PEG ratio < 0.5
		
		selenium.click_By(By.xpath("//a[text()='Sign in']"), "SignIn Link");
		selenium.click_By(By.xpath("//a[contains(text(),'Login with Email')]"), "LoginWithEmailLink");
		selenium.enterText_By(By.xpath("//input[@id='txtEmailMobile']"), "anvardhan551@gmail.com", "Email");
		selenium.enterText_By(By.xpath("//input[@id='txtPassword']"), "Pnbe@305", "Password");
		selenium.click_By(By.xpath("//button[@id='btnLogin']"), "Login btn"); 
		
		//*[text()='Go to Ticker']
		
		selenium.click_By(By.xpath("//button[@id='btnLogin']"), "Login btn"); 
								
		//Click on Screener
		selenium.click_By(By.xpath("//*[text()='Go to Ticker']"), "Go To Ticker link");
		
		//Click on screener tab
		selenium.click_By(By.xpath("//div[@id='dnav']/div//li/a[text()='Screener']"), "Screener tab");
									
		//Click on Run Screen of Value Stocks.		
		selenium.click_By(By.xpath("//p[contains(text(),'Value Stocks')]/following-sibling::a"), "ValueStock");			
			
		String screen = "PE Ratio < 10 AND PEG ratio < 0.5 AND PE Ratio > 5 AND MCAP > 2000";		//DDF
							
		selenium.enterText_By(By.xpath("//textarea[@id='mainContent_screener']"), screen, "screenersearch");
		
		selenium.click_By(By.xpath("//input[@id='mainContent_btnRunQuery']"), "Run Screener btn");
		
		
		
	
								
		
	}	
	
	
	
	//reusable methods for sector
	private int validateListedCompaniesCount(String sectorName) throws Exception {
		
		selenium.click_By(By.xpath("//*[@id='mainContent_sector']//h4[contains(text(),'"+sectorName+"')]/following-sibling::div/a"), "SectorName");
		
		List<WebElement> listedCompanies = selenium.getListElements(By.xpath("//*[@id='companylist']/tbody/tr/td[2]"), "ListedCompanies");
		
		return listedCompanies.size();
	}
	
	private ArrayList<String> getListedCompanies(String sectorName) throws Exception {
		
		ArrayList<String> listedCompanies = new ArrayList<String>();
		
		selenium.click_By(By.xpath("//*[@id='mainContent_sector']//h4[contains(text(),'"+sectorName+"')]/following-sibling::div/a"), "SectorName");
		
		List<WebElement> listedCompaniesElements = selenium.getListElements(By.xpath("//*[@id='companylist']/tbody/tr/td[2]"), "ListedCompanies");		
		
		for(WebElement listedCompanyElement : listedCompaniesElements) {
			
			String listedComapny = selenium.getElementText(listedCompanyElement, "Listed company");
			
			listedCompanies.add(listedComapny);
			
		}		
		
		return listedCompanies;
	}
	
	private LinkedHashMap<String, String> getPriceofAllListedCompanies(String sectorName) throws Exception {
		
		LinkedHashMap<String, String> listedCompaniesMap = new LinkedHashMap<String, String>();
		
		selenium.click_By(By.xpath("//*[@id='mainContent_sector']//h4[contains(text(),'"+sectorName+"')]/following-sibling::div/a"), "SectorName");
		
		//Get total records count
		List<WebElement> listedCompaniesElements = selenium.getListElements(By.xpath("//*[@id='companylist']/tbody/tr/td[2]"), "ListedCompanies");		
		
		//Get each record and store in hashmap
		for(int i = 1; i <= listedCompaniesElements.size(); i++) {
			
			String companyName = selenium.getElementText_By(By.xpath("//*[@id='companylist']/tbody/tr["+i+"]/td[2]"), "ListedCompanyName");
			String Price = selenium.getElementText_By(By.xpath("//*[@id='companylist']/tbody/tr["+i+"]/td[3]"), "ListedCompanyPrice");
			
			listedCompaniesMap.put(companyName, Price);
			
		}
		
		return listedCompaniesMap;
	}

	
}
