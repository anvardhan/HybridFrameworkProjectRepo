package com.qa.tests;

import java.util.Iterator;
import java.util.Set;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.qa.app.utils.WebElementUtil;
import com.qa.pages.FinologyHomePage;

public class FinologyHomeTest extends BaseTest {	
	

	//Adding test scripts related to Finology Home Test - Home page of all app

	
	//Home Page
	//@Test (retryAnalyzer = com.qa.app.listeners.RetryAnalyzer.class, enabled = true, priority = 1, groups = {"sanity", "mandatory"}, description = "This test is to validate Ticker App url")
	@Test (enabled = true, priority = 1, groups = {"sanity", "mandatory"}, description = "This test is to validate Ticker App url")
	public void tickerProduct_validateTickerUrl() throws Exception {
		
		/*driver.findElement(By.xpath("//*[text()='Products']")).click();
		selenium.explicitWait_By(By.xpath("//span[text()='Ticker']"), 30, "PRESENSE");
		driver.findElement(By.xpath("//span[text()='Ticker']")).click();*/
		
		/*selenium.click_By(By.xpath("//*[text()='Products']"), "Products tab");
		selenium.explicitWait_By(By.xpath("//span[text()='Ticker']"), 30, "PRESENSE");
		selenium.click_By(By.xpath("//span[text()='Ticker']"), "Ticker App");*/
		
		Reporter.log("This Test is to validate ticker App url which is a new window");
		
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.tickerApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.tickerApp, "Ticker App");
		
		//A new window will open
		
		String actualFinologyUrl = selenium.getCurrentUrl();
		//actualFinologyUrl = "https://www.finology.india/";
		
		System.out.println(selenium.getCurrentUrl());  //https://www.finology.in/ because window focus is on main window
		
		if(actualFinologyUrl.equals("https://www.finology.in/")) {
			System.out.println("Pass - Finology Url is as expected");
			Reporter.log("Pass - Finology Url is as expected");
		} else {
			System.out.println("Fail - Finology Url is NOT as expected");
			Reporter.log("Fail - Finology Url is NOT as expected");
		}
		
		//Hard Assertion
		//Assert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected: ");
		
		//Soft Assertion				
		softassert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected: ");
		
		String parentWindowId = selenium.getParentWindowId();
		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		
		/*for(String windowid : allWindowId) {
			
			if(!windowid.equals(parentWindowId)) {
				selenium.switchToChildWindow(windowid);
				
				System.out.println(selenium.getCurrentUrl()); //https://ticker.finology.in/
				
			}			
		}*/
		
		String childTickerWindow = null;
		
		Iterator<String> itr = allWindowId.iterator();
		
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
		
		selenium.switchToChildWindow(childTickerWindow);
		
		String actualTickerUrl = selenium.getCurrentUrl();
		System.out.println(actualTickerUrl); //https://ticker.finology.in/
		//actualTickerUrl = "https://ticker.finology.india/";
		
		if(actualTickerUrl.equals("https://ticker.finology.in/")) {
			System.out.println("Pass - Ticker Url is as expected");
		} else {
			System.out.println("Fail - Ticker Url is NOT as expected");
		}
		
		//Hard Assertion
		//Assert.assertEquals(actualTickerUrl, "https://ticker.finology.in/", "Ticker url is icorrect");
		
		//Soft Assertion
		softassert.assertEquals(actualTickerUrl, "https://ticker.finology.in/", "Ticker url is icorrect");
		
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
		
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		
		System.out.println(selenium.getCurrentUrl());
		actualFinologyUrl = selenium.getCurrentUrl();
		if(actualFinologyUrl.equals("https://www.finology.in/")) {
			System.out.println("Pass - Finology Url is as expected after moving back from ticker App");
		} else {
			System.out.println("Fail - Finology Url is NOT as expected after moving back from ticker App");
		}
		
		//Hard Assertion
		//Assert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected after back from ticker App");
		
		//Soft Assertion
		softassert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected after back from ticker App");
	
		//Mandatory step to wrap soft assertion
		
		softassert.assertAll();
	
	}
	
	@Test (enabled = true, priority=2, groups = {"sanity", "regression"},description = "This test is to validate Select App url")
	public void selectProduct_validateSelectUrl() throws Exception {
			
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
			
		System.out.println(selenium.getCurrentUrl());  //https://www.finology.in/ because window focus is on main window
		String actualFinologyUrl = selenium.getCurrentUrl();
		
		softassert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected: ");
		
		String parentWindowId = selenium.getParentWindowId();
			
		Set<String> allWindowId = selenium.getAllWindowHandles();
						
		String childTickerWindow = null;
			
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		String actualSelectUrl = selenium.getCurrentUrl();
		actualSelectUrl = "//https://select.finology.india/";
		System.out.println(actualSelectUrl); //https://select.finology.in/
			
		if(actualSelectUrl.equals("https://select.finology.in/")) {
			System.out.println("Pass - Select Url is as expected");
		} else {
			System.out.println("Fail - Select Url is NOT as expected");
			WebElementUtil.takeScreenshots("selectProduct_validateSelectUrl");
		}
		
		softassert.assertEquals(actualSelectUrl, "https://select.finology.in/", "Select url is not as expected: ");
			
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		
		System.out.println(selenium.getCurrentUrl());
		actualFinologyUrl = selenium.getCurrentUrl();
		softassert.assertEquals(actualFinologyUrl, "https://www.finology.in/", "Finology url is not as expected: ");
		
		softassert.assertAll();
			
	}
	
	@Test (enabled = false, description = "This test is to validate Quest App url")
	public void questProduct_validateQuestUrl() throws Exception {
			
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.questApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.questApp, "Quest App");
			
		System.out.println(selenium.getCurrentUrl());  //https://www.finology.in/ because window focus is on main window
			
		String parentWindowId = selenium.getParentWindowId();
			
		Set<String> allWindowId = selenium.getAllWindowHandles();
						
		String childTickerWindow = null;
			
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		String actualQuestUrl = selenium.getCurrentUrl();
		System.out.println(actualQuestUrl); //https://quest.finology.in/
			
		if(actualQuestUrl.equals("https://quest.finology.in/")) {
			System.out.println("Pass - quest Url is as expected");
		} else {
			System.out.println("Fail - quest Url is NOT as expected");
		}
			
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		
		System.out.println(selenium.getCurrentUrl());
			
	}
	
	@Test (enabled = false)
	public void recipeProduct_validateQuestUrl() throws Exception {
			
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.recipeApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.recipeApp, "Recipe App");
			
		System.out.println(selenium.getCurrentUrl());  //https://www.finology.in/ because window focus is on main window
			
		String parentWindowId = selenium.getParentWindowId();
			
		Set<String> allWindowId = selenium.getAllWindowHandles();
						
		String childTickerWindow = null;
			
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		String actualRecipeUrl = selenium.getCurrentUrl();
		System.out.println(actualRecipeUrl); //https://recipe.finology.in/
			
		if(actualRecipeUrl.equals("https://recipe.finology.in/")) {
			System.out.println("Pass - recipe Url is as expected");
		} else {
			System.out.println("Fail - recipe Url is NOT as expected");
		}
			
		browserDriverFactory.closeBrowser(); //Close Ticker url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		
		System.out.println(selenium.getCurrentUrl());
			
	}
	
	

	

}
