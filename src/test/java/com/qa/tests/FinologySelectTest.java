package com.qa.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.app.utils.ExcelUtilities;
import com.qa.pages.FinologyHomePage;
import com.qa.pages.FinologySelectPage;

public class FinologySelectTest extends BaseTest {
	
	
	//Adding test scripts related to Select App1
	
	//Select Home Page
	@Test (enabled = true, priority=1, groups = {"sanity", "regression"})
	public void SelectProduct_validateSelectLogo() throws Exception {
					
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
			
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		boolean logoDisplay = selenium.verifyElementDisplay_By(FinologySelectPage.selectImgLogo, "Select logo");
		
		if(logoDisplay) {
			System.out.println("Pass - Select Logo displayed as expected");
		} else {
			System.out.println("Fail - Select Logo Not displayed not as expected");
		}
		
		Assert.assertTrue(logoDisplay, "Select Logo Not displayed not as expected");
	
		browserDriverFactory.closeBrowser(); //Close Select url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		
		//Assert.assertTrue(false);
		
		}	
	
	//Select Home/Brokers Page
	@Test (enabled = true)
	public void SelectProduct_validateBrokersCount() throws Exception {
				
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
		
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
		
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
		
		selenium.switchToChildWindow(childTickerWindow);
		
		selenium.explicitWait_By(FinologySelectPage.brokersTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.brokersTab, "Brokers Link");
		
		//expected count of brokers = 21
		
		String brokerName = selenium.getElementText_By(FinologySelectPage.zerodhaBroker, "Zerodha");
		System.out.println("BrokerName: "+brokerName); //Zerodha
		
		List<WebElement> brokerListElement = selenium.getListElements(FinologySelectPage.brokerList, "BrokerList");
		
		int actualBrokersCount = brokerListElement.size();
		
		if(actualBrokersCount == 21) {
			System.out.println("Pass - Total brokers actual count matched with expected brokers count");
		} else {
			System.out.println("Fail - Total brokers actual count Not matched with expected brokers count");
		}	
		
		Assert.assertEquals(actualBrokersCount, 21, "Total brokers actual count Not matched with expected brokers count");
		
		browserDriverFactory.closeBrowser(); //Close Select url browser
		
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Select Home/Brokers Page
	@Test (enabled = false)
	public void SelectProduct_validateBrokersList() throws Exception {
					
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
			
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
			
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
			
		selenium.switchToChildWindow(childTickerWindow);
			
		selenium.explicitWait_By(FinologySelectPage.brokersTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.brokersTab, "Brokers Link");
					
		List<WebElement> brokerListElement = selenium.getListElements(FinologySelectPage.brokerList, "BrokerList");
			
		String expectedBrokersList = "Zerodha Direct;Upstox;Groww;Edelweiss;Angel One;ICICI Direct";
		String[] expectedBrokersListSplit = expectedBrokersList.split(";");
		
		String expectedBrokerName = null;
		String actualBrokerName = null;
				
		for(int i = 0 ; i < expectedBrokersListSplit.length ; i++) {
			
			expectedBrokerName = expectedBrokersListSplit[i];
			
			boolean found = false;
	
			for(WebElement brokerElement : brokerListElement) {
				
				actualBrokerName = selenium.getElementText(brokerElement, "BrokerName");
				
				if(actualBrokerName.equalsIgnoreCase(expectedBrokerName)) {
					System.out.println("Pass: actual broker: '"+actualBrokerName+"' - expected broker: '"+expectedBrokerName+"'");
					found = true;
					break;
				}			
				
			}
			
			if(!found) {
				System.out.println("Fail: Expected Broker: '"+expectedBrokerName+"' NOT found");
			}
			
		}
					
		browserDriverFactory.closeBrowser(); //Close Select url browser
			
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}	
	
	//Select Home/Brokers Page
	@Test (enabled = false)
	public void SelectProduct_validateBrokerNameAndTypeMethod1() throws Exception {
						
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
				
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
				
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
				
		selenium.switchToChildWindow(childTickerWindow);
				
		selenium.explicitWait_By(FinologySelectPage.brokersTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.brokersTab, "Brokers Link");
		
		//Xpath used targets only brokerName
		List<WebElement> brokerNameListElement = selenium.getListElements(FinologySelectPage.brokerList, "BrokerList");
		
		//List<WebElement> brokerTypeElement = selenium.getListElements(By.xpath("//section[contains(@class,'brokerslist')]//h4/a[1]/parent::h4/following-sibling::div[1]//span[@class='badge badge-primary-light']"), "BrokerType");
	
		int discountBrokerCount = 0;
		int fullserviceBrokerCount = 0;
		
		for(int i = 0;  i < brokerNameListElement.size(); i++) {
			
			//WebElement brokerNameElement = brokerNameListElement.get(i);			
			//String brokerName = selenium.getElementText(brokerNameElement, "brokerName");
			
			//System.out.println(brokerName);
	
			//Xpath used to get brokertype
			WebElement brokerTypeElement = selenium.getElement(By.xpath("(//section[contains(@class,'brokerslist')]//h4/a[1]/parent::h4/following-sibling::div[1]//span[@class='badge badge-primary-light'])["+(i+1)+"]"), "BrokerType");
			
			String brokerType = selenium.getElementText(brokerTypeElement, "brokerType");
			
			if(brokerType.equalsIgnoreCase("Discount Broker"))
				discountBrokerCount++;
			else
				fullserviceBrokerCount++;
			
			
			//System.out.println("brokerName: "+brokerName+" - brokerType: "+brokerType);
		
		
		}
		
		System.out.println("discountBrokerCount: "+discountBrokerCount);
		System.out.println("fullserviceBrokerCount: "+fullserviceBrokerCount);
		
		if(discountBrokerCount >= 5 && fullserviceBrokerCount >= 5) {
			System.out.println("Pass - discountbrokerCount/fullservicebrokercount has more than 5 in list");
		} else {
			System.out.println("Fail - discountbrokerCount/fullservicebrokercount has less than 5 in list");
		}
		
		
					
		browserDriverFactory.closeBrowser(); //Close Select url browser
				
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Select Home/Brokers Page
	@Test (enabled = false)
	public void SelectProduct_validateBrokerNameAndTypeMethod2() throws Exception {
							
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
					
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
					
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
					
		selenium.switchToChildWindow(childTickerWindow);
					
		selenium.explicitWait_By(FinologySelectPage.brokersTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.brokersTab, "Brokers Link");
		
		//Xpath used here covers all element of a broker including name and type.
		List<WebElement> brokerListElement = selenium.getListElements(FinologySelectPage.allBrokerList, "BrokerList");
		
		int discountBrokerCount = 0;
		int fullserviceBrokerCount = 0;
			
		for(int i = 0;  i < brokerListElement.size(); i++) {
		
			WebElement brokerElement = brokerListElement.get(i);
			
			WebElement brokerNameElement = brokerElement.findElement(By.xpath("(//h4/a[1])["+(i+1)+"]"));			
			String brokerName = selenium.getElementText(brokerNameElement, "brokerName");
		
			WebElement brokerTypeElement = brokerElement.findElement(By.xpath("(//span[@class='badge badge-primary-light'])["+(i+1)+"]"));
			String brokerType = selenium.getElementText(brokerTypeElement, "brokerType");
				
			if(brokerType.equalsIgnoreCase("Discount Broker"))
				discountBrokerCount++;
			else
				fullserviceBrokerCount++;
				
				
			System.out.println("brokerName: "+brokerName+" - brokerType: "+brokerType);
		
			
		}
		
		System.out.println("discountBrokerCount: "+discountBrokerCount);
		System.out.println("fullserviceBrokerCount: "+fullserviceBrokerCount);
			
		if(discountBrokerCount >= 5 && fullserviceBrokerCount >= 5) {
			System.out.println("Pass - discountbrokerCount/fullservicebrokercount has more than 5 in list");
		} else {
			System.out.println("Fail - discountbrokerCount/fullservicebrokercount has less than 5 in list");
		}
			
			
						
		browserDriverFactory.closeBrowser(); //Close Select url browser
					
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	
	//Select Home/Calculators Page
	@Test (enabled = false)
	public void SelectProduct_validateLaunchCalculator_Calculators_Approach1() throws Exception {
							
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
					
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
					
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
					
		selenium.switchToChildWindow(childTickerWindow);
					
		selenium.explicitWait_By(FinologySelectPage.calculatorsTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.calculatorsTab, "Calculators Link");
		
		//Xpath used here covers all element of a broker including launch calculator.
		List<WebElement> brokerListElement = selenium.getListElements(FinologySelectPage.calculatorsBrokerList, "BrokerList");
		
		//section[contains(@class,'brokerslist')]//h4[text()='Zerodha']/parent::div/following-sibling::div//a
		
		for(int i = 0;  i < brokerListElement.size(); i++) {
		
			WebElement brokerElement = brokerListElement.get(i);
			
			WebElement brokerNameElement = brokerElement.findElement(By.xpath("(//h4)["+(i+1)+"]"));
			
			String brokerName = selenium.getElementText(brokerNameElement, "brokerName");
			
			if(brokerName.equalsIgnoreCase("ICICI Direct")) {
				
				WebElement launchCalculatorElement = brokerElement.findElement(By.xpath("(//section[contains(@class,'brokerslist')]//a[text()='Launch Calculator'])["+(i+1)+"]"));
				selenium.click(launchCalculatorElement, "launch calculator element");
				
				
				String calulatorBrokHeader = selenium.getElementText_By(FinologySelectPage.calculatorBrokerHeader, "Header");
				
				//validate right calculator launched or not
				if(calulatorBrokHeader.contains("ICICI Direct")) {
					System.out.println("Pass - Launched right calculator-Approach1");
				} else {
					System.out.println("Pass - Launched incorrect calculator-Approach1");
				}
				
				break;
				
			}
		}
		
		Thread.sleep(4000);		
		browserDriverFactory.closeBrowser(); //Close Select url browser
					
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Select Home/Calculators Page
	@Test (enabled = false, priority=2, groups = {"regression"}, dataProvider = "Calculator_ICICI", dependsOnMethods = {"SelectProduct_validateSelectLogo", "SelectProduct_validateBrokersCount"})
	public void SelectProduct_validateBrokerageRateAndAmount_Calculators_Approach2ForLaunchCalculator_bkp(String brokPlan, 
			String quant, String price, String brokRate) throws Exception {
								
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
						
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
						
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
						
		selenium.switchToChildWindow(childTickerWindow);
						
		selenium.explicitWait_By(FinologySelectPage.calculatorsTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.calculatorsTab, "Calculators Link");
			
		//Xpath used to get launch calculator element of specific broker		
		//section[contains(@class,'brokerslist')]//h4[text()='Zerodha']/parent::div/following-sibling::div//a
				
		selenium.click_By(FinologySelectPage.launchCalculatorICICIDirect, "Launch Calculator Link");
			
		String calulatorBrokHeader = selenium.getElementText_By(FinologySelectPage.calculatorBrokerHeader, "Header");
		
		//validate right calculator launched or not
		if(calulatorBrokHeader.contains("ICICI Direct")) {
			System.out.println("Pass - Launched right calculator");
		} else {
			System.out.println("Pass - Launched incorrect calculator");
		}
		
		//ICICI Calculator Test Data
		/*String brokeragePlan = "Prime 1";
		String Quantity = "250";
		String price = "300";
		String brokerageRate = "0.27"; */
		
		String brokeragePlan = brokPlan;
		String Quantity = quant;
		String buyPrice = price;
		String brokerageRate = brokRate;
		
		String expectedBrokerageRate = brokerageRate+"% on order value";
		
		Float expectedBrokerageCharge = (Integer.parseInt(Quantity)*Float.parseFloat(buyPrice)*Float.parseFloat(brokerageRate))/100;
		System.out.println("expectedBrokerageCharge: "+expectedBrokerageCharge);
		
		//Enter data in calculator
		
		
		selenium.click_By(FinologySelectPage.calculatorDeliveryTab, "Delivery tab");		
		selenium.selectDropdownByVisibleText_By(FinologySelectPage.calculatorPlanDrpDown, brokeragePlan, "BrokeragePlan drpdwn");		
		selenium.enterText_By(FinologySelectPage.calculatorQuantity, Quantity, "Quantity element");		
		selenium.enterText_By(FinologySelectPage.calculatorPrice, price, "Price element");
		selenium.selectCheckBox(FinologySelectPage.calculatorNseRadBtn, "nse checkbox element");
		selenium.click_By(FinologySelectPage.calculateBtn, "Calculate brokerage");
		
		//Get values from results and validate
		
		Thread.sleep(2000);
				
		String actualBrokerageRate = selenium.getElementText_By(FinologySelectPage.brokerageRate, "brokerage rate element");		
		String actualBrokerageCharge1 = selenium.getElementText_By(FinologySelectPage.brokerageCharge, "brokerage charge");
		
		String[] actualBrokerageChargeSplit = actualBrokerageCharge1.split("\\s+");
		Float actualBrokerageCharge = Float.parseFloat(actualBrokerageChargeSplit[1]);
		System.out.println("actualBrokerageCharge: "+actualBrokerageCharge);
		
		System.out.println("actualBrokerageRate: "+actualBrokerageRate.trim());
				
		if(actualBrokerageRate.trim().equalsIgnoreCase(expectedBrokerageRate)) {
			System.out.println("Pass - Brokerage rate is as expected");
		} else {
			System.out.println("Fail - Brokerage rate is NOT as expected");
		}
		
		//expectedBrokerageCharge: 202.5
		//actualBrokerageCharge: 202.5
		
		/*if(actualBrokerageCharge == expectedBrokerageCharge) {
			System.out.println("Pass - Brokerage charge is as expected");
		} else {
			System.out.println("Fail - Brokerage charge is NOT as expected");
		}*/
		
		if(Float.compare(actualBrokerageCharge, expectedBrokerageCharge) == 0) { 
			System.out.println("Pass - Brokerage charge is as expected");
		} else {
			System.out.println("Fail - Brokerage charge is NOT as expected");
		}
		
									
		browserDriverFactory.closeBrowser(); //Close Select url browser
						
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
	}
	
	//Select Home/Calculators Page
	@Test (enabled = true, priority=2, groups = {"regression"}, dataProvider = "Calculator_ICICI")
	public void validateBrokerageRateAndAmount_Calculators(String brokPlan, String quant, String price, String brokRate) throws Exception {
									
		selenium.click_By(FinologyHomePage.productTab, "Products tab");
		selenium.explicitWait_By(FinologyHomePage.selectApp, 30, "PRESENSE");
		selenium.click_By(FinologyHomePage.selectApp, "Select App");
							
		String parentWindowId = selenium.getParentWindowId();		
		Set<String> allWindowId = selenium.getAllWindowHandles();
		String childTickerWindow = null;		
		Iterator<String> itr = allWindowId.iterator();
							
		if(itr.hasNext())
			itr.next(); //This will return parant window
		if(itr.hasNext())
			childTickerWindow = itr.next(); //This will return child window
							
		selenium.switchToChildWindow(childTickerWindow);
							
		selenium.explicitWait_By(FinologySelectPage.calculatorsTab, 30, "CLICKABLE");
		selenium.click_By(FinologySelectPage.calculatorsTab, "Calculators Link");
				
		//Xpath used to get launch calculator element of specific broker		
		//section[contains(@class,'brokerslist')]//h4[text()='Zerodha']/parent::div/following-sibling::div//a
					
		selenium.click_By(FinologySelectPage.launchCalculatorICICIDirect, "Launch Calculator Link");
				
		String calulatorBrokHeader = selenium.getElementText_By(FinologySelectPage.calculatorBrokerHeader, "Header");
			
		//validate right calculator launched or not
		if(calulatorBrokHeader.contains("ICICI Direct")) {
			System.out.println("Pass - Launched right calculator");
			Assert.assertTrue(true);
		} else {
			System.out.println("Pass - Launched incorrect calculator");
			Assert.assertFalse(false);			
		}
			
		//ICICI Calculator Test Data
		/*String brokeragePlan = "Prime 1";
		String Quantity = "250";
		String price = "300";
		String brokerageRate = "0.27"; */
		
		//Data from excel and data provider	
		String brokeragePlan = brokPlan;
		String Quantity = quant;
		String buyPrice = price;
		String brokerageRate = brokRate;
			
		String expectedBrokerageRate = brokerageRate+"% on order value";
			
		Float expectedBrokerageCharge = (Integer.parseInt(Quantity)*Float.parseFloat(buyPrice)*Float.parseFloat(brokerageRate))/100;
		System.out.println("expectedBrokerageCharge: "+expectedBrokerageCharge);
		
		//Enter data in calculator
		
		
		selenium.click_By(FinologySelectPage.calculatorDeliveryTab, "Delivery tab");		
		selenium.selectDropdownByVisibleText_By(FinologySelectPage.calculatorPlanDrpDown, brokeragePlan, "BrokeragePlan drpdwn");		
		selenium.enterText_By(FinologySelectPage.calculatorQuantity, Quantity, "Quantity element");		
		selenium.enterText_By(FinologySelectPage.calculatorPrice, price, "Price element");
		selenium.selectCheckBox(FinologySelectPage.calculatorNseRadBtn, "nse checkbox element");
		selenium.click_By(FinologySelectPage.calculateBtn, "Calculate brokerage");
			
		//Get values from results and validate
		
		Thread.sleep(2000);
				
		String actualBrokerageRate = selenium.getElementText_By(FinologySelectPage.brokerageRate, "brokerage rate element");		
		String actualBrokerageCharge1 = selenium.getElementText_By(FinologySelectPage.brokerageCharge, "brokerage charge");
			
		String[] actualBrokerageChargeSplit = actualBrokerageCharge1.split("\\s+");
		Float actualBrokerageCharge = Float.parseFloat(actualBrokerageChargeSplit[1]);
		System.out.println("actualBrokerageCharge: "+actualBrokerageCharge);
			
		System.out.println("actualBrokerageRate: "+actualBrokerageRate.trim());
					
		if(actualBrokerageRate.trim().equalsIgnoreCase(expectedBrokerageRate)) {
			System.out.println("Pass - Brokerage rate is as expected");
		} else {
			System.out.println("Fail - Brokerage rate is NOT as expected");
		}
			
		//expectedBrokerageCharge: 202.5
		//actualBrokerageCharge: 202.5
			
		/*if(actualBrokerageCharge == expectedBrokerageCharge) {
			System.out.println("Pass - Brokerage charge is as expected");
		} else {
			System.out.println("Fail - Brokerage charge is NOT as expected");
		}*/
			
		if(Float.compare(actualBrokerageCharge, expectedBrokerageCharge) == 0) { 
			System.out.println("Pass - Brokerage charge is as expected");
		} else {
			System.out.println("Fail - Brokerage charge is NOT as expected");
		}
			
										
		browserDriverFactory.closeBrowser(); //Close Select url browser
							
		//Move back to Parent
		selenium.switchToParentWindow(parentWindowId);
		}
		
	@DataProvider (name = "Calculator_ICICI")
	public Object[][] getICICICalculatorData() throws IOException {
		
		String filePath = null;
		String sheetName = null;
		
		//Running test data for specific environment through Maven/Jenkins - use environments as needed
		//mvn clean install -DfilePath="dev"
		
		/*String excelPath = System.getProperty("filePath");  //value should be qa, dev, stage or uat
		
		if (excelPath == null) {
			System.out.println("Test Data taken from: " + excelPath);
			filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect - qa.xlsx";
			
		} else {
			System.out.println("Test Data taken from: " + excelPath);
			switch (excelPath.toLowerCase()) {
			case "qa":
				filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect - qa.xlsx";
				break;
			case "dev":
				filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect - dev.xlsx";
				break;
			case "stage":
				filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect - stage.xlsx";
				break;
			case "uat":
				filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect - uat.xlsx";
				break;

			default:
				System.out.println("please pass the right excelpath for test data.....");
				break;
			}
		}*/
		
		
		filePath = ".\\src\\test\\resources\\TestData\\TestData_FinologySelect.xlsx";
		sheetName = "Calculator_ICICI";
		
		ExcelUtilities.setExcelFile(filePath, sheetName);
		ArrayList<HashMap<String,String>> readExcelData = ExcelUtilities.readExcelData();
		HashMap<String,String> rowData = null;
		
		Object[][] CalculatorData = new Object[readExcelData.size()][4];
		
		for (int i = 0; i < readExcelData.size(); i++) {
			
			rowData = readExcelData.get(i);
			
			/*String brokeragePlan = rowData.get("BrokeragePlan");
			String quantity = rowData.get("Quantity");
			String price = rowData.get("Price");
			String brokerageRate = rowData.get("BrokerageRate"); */
			
			CalculatorData[i][0] =  rowData.get("BrokeragePlan");
			CalculatorData[i][1] =  rowData.get("Quantity");
			CalculatorData[i][2] =  rowData.get("Price");
			CalculatorData[i][3] =  rowData.get("BrokerageRate");
		}		
		
		return CalculatorData;		
		
	}
	
		
}
