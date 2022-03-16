package com.qa.app.utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.app.base.BrowserDriverFactory;

public class WebElementUtil {
	
	public static WebDriver driver = null;
	public static Properties prop = null;
	
	//constructor
	public WebElementUtil(WebDriver driver) {
		
		WebElementUtil.driver = driver;
		prop = new Properties();
		
	}
		
	/**
	 * This method searches the element in the application by using the By class locators and returns the 1st instance of the element on successful search
	 * @Name getElement
	 * @param objLocator The By class locator of the element to be searched  
	 * @param objName The Element Name to be searched
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception 
	 * @description It returns the 1st instance of the WebElement available in the Web page
	 * @return WebElement                                        
	 */
	public WebElement getElement(By objLocator, String objName) throws Exception
	{
		WebElement element=null;
		try 
		{
			element=driver.findElement(objLocator);
		}
		catch (Exception e)
		{	
			//objReport.setValidationMessageInReport("FAIL", "Method getElement : Failed to locate element '"+objName+"' using  selenium locator: "+objLocator.toString());
		}

		return element;
	}
	
	/**
	 * This method searches the element in the application by using the By class locators and returns the list object containing all instance of the element available in application page  on successful search
	 * @Name getListElements
	 * @param objLocator The By class object of the element to be searched  
	 * @param objName The Element Name to be searched
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception 
	 * @description This method searches the element in the application by using the By class locators and returns the list object containing all instance of the element available in application page  on successful search
	 * @return List<WebElement>                                        
	 */
	public List<WebElement> getListElements(By objLocator, String objName) throws Exception
	{
		List<WebElement> listElements=new ArrayList<>();
		try 
		{
			listElements=driver.findElements(objLocator);
		}				
		catch (Exception e)
		{			
			//objReport.setValidationMessageInReport("FAIL", "Method getListElements : Failed to locate '"+objName+"' element due to exception : "+e);
		}
		//System.out.println(lstElements.size());
		return listElements;
	}
	
	/**
	 * This method checks element visibility in the Web page or Mobile App  
	 * @Name getElementCount
	 * @param obj - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks the size of the Web Element list and returns its count
	 * @return  Integer                                               
	 */
	public int getElementCount(By obj,String objName) throws Exception
	{
		try{
			List<WebElement> elementList = getListElements(obj,objName);
			int elementListSize= elementList.size();
			return elementListSize;
		}

		catch(NoSuchElementException e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'getElementCount' : Failed to get count of '"+objName + "' element due to Exception '"+e+"'");
			return 0;
		}
	}
	
	
	
	/**
	 * Enters the text in the text box
	 * @Name enterText
	 * @param txtBoxElement The WebElement of the text box element where value to be entered 
	 * @param strText The text to be entered in the text box
	 * @param objName The text box Element Name where value to be entered
	 * @return boolean
	 * @applicableTo  Web 
	 * @description This method clears the text box and then enters the value (strText) provided by user                                       
	 */
	public boolean enterText(WebElement txtBoxElement, String strText, String objName) throws Exception
	{
		try {
			boolean result = true;
			txtBoxElement.clear();			
			txtBoxElement.sendKeys(strText);
			//Thread.sleep(5000);
			//objReport.setValidationMessageInReport("PASS", strText + " text is entered in the '"+ objName + "' textbox ");
			return result;
		}
		catch(Exception e) 
		{
			//objReport.setValidationMessageInReport("FAIL", "Failed to enter text in '"+ objName + "' textbox");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Enters the text in the text box
	 * @Name enterText
	 * @param obj The By object of the text box element where value to be entered 
	 * @param strText The text to be entered in the text box
	 * @param objName The text box Element Name where value to be entered
	 * @return boolean
	 * @applicableTo  Web 
	 * @description This method clears the text box and then enters the value (strText) provided by user                                       
	 */
	public boolean enterText_By(By obj, String strText, String objName) throws Exception
	{
		try {
			boolean result = true;
			//By txtBoxBy = By.xpath("//*[@id='idval']");
			//Pass by object below
			//driver.findElement(obj).clear();
			//driver.findElement(obj).sendKeys(strText); 
			
			WebElement txtBoxElement = getElement(obj, objName);
			txtBoxElement.clear();
			txtBoxElement.sendKeys(strText);
			
			//Thread.sleep(5000);
			//objReport.setValidationMessageInReport("PASS", strText + " text is entered in the '"+ objName + "' textbox ");
			return result;
		}
		catch(Exception e) 
		{
			//objReport.setValidationMessageInReport("FAIL", "Failed to enter text in '"+ objName + "' textbox");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method clears the text in the specified text box object
	 * @Name clearTextbox     
	 * @param txtBoxElement of The WebElement class object of the text box element
	 * @param objName Name of the Text box WebElement
	 * @Applicable Desktop,Mobile 
	 * @description This method clears the text in the specified object
	 */
	public void clearTextbox(WebElement txtBoxElement, String objName) throws Exception
	{
		try{
			txtBoxElement.clear();	
			//objReport.setValidationMessageInReport("PASS", "Value is removed from '"+ objName + "' textbox");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Failed to remove value from '"+ objName + "' textbox due to Exception : "+e);	
		}

	}
	
	/**
	 * This method clears the text in the specified text box object
	 * @Name clearTextbox     
	 * @param obj of The By class object of the text box element
	 * @param objName Name of the Text box WebElement
	 * @Applicable Desktop,Mobile 
	 * @description This method clears the text in the specified object
	 */
	public void clearTextbox_By(By obj, String objName) throws Exception
	{
		try{
			getElement(obj, objName).clear();	
			//objReport.setValidationMessageInReport("PASS", "Value is removed from '"+ objName + "' textbox");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Failed to remove value from '"+ objName + "' textbox due to Exception : "+e);	
		}

	}
	
	/**
	 * Clicks the element(Button,Link,Image)
	 * @Name click
	 * @param btnOrLinkElement The WebElement of the element to be clicked  
	 * @param objName The Element Name to be clicked
	 * @return boolean     
	 * @applicableTo  Desktop,Mobile   
	 * @description Clicks the element(Button,Link,Image)                              
	 */
	public void click(WebElement btnOrLinkElement, String objName) throws Exception
	{
		try {
			btnOrLinkElement.click();
			//Thread.sleep(5000);
		}
		catch(Exception e) 
		{
			//objReport.setValidationMessageInReport("FAIL", "Failed to click '"+objName + "' button due to exception : "+e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Clicks the element(Button,Link,Image)
	 * @Name click
	 * @param objLocator The By class object of the element to be clicked  
	 * @param objName The Element Name to be clicked
	 * @return boolean     
	 * @applicableTo  Desktop,Mobile   
	 * @description Clicks the element(Button,Link,Image)                              
	 */	
	public void click_By(By obj, String objName) throws Exception
	{
		try {
			//driver.findElement(obj).click();
			
			WebElement btnLinkElement=getElement(obj,objName);
			btnLinkElement.click();			
		}
		catch(Exception e) 
		{
			//objReport.setValidationMessageInReport("FAIL", "Failed to click '"+objName + "' button due to exception : "+e.toString());
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void typeRandomNumber(WebElement element, int data) {
		Random random = new Random();
		int rn = random.nextInt(100);
		String randomNumber = Integer.toString(rn);
		element.sendKeys(randomNumber);
	}
	
	
	
	
	
	//Get text, attribute value, default value from text box
	
	/**
	 * This method returns the text value of the WebElement specified by objName/obj
	 * @Name getElementText         
	 * @param element -   WebElement element locator of web element
	 * @param objName - Name of the WebElement
	 * @return String 
	 * @throws Exception
	 * @applicableTo Desktop, Mobile
	 * @description This method returns the text value of the WebElement specified by objName/obj                                                    
	 */		
	public String getElementText(WebElement element , String objName) throws Exception
	{
		try
		{			
			return element.getText().trim();		
		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method 'getElementText' : Failed to get the text of '"+objName + "' due to Exception : "+e);
			return null;
		}
	}
	
	/**
	 * This method returns the text value of the WebElement specified by objName/obj
	 * @Name getElementText         
	 * @param obj -   By element locator of web element
	 * @param objName - Name of the WebElement
	 * @return String 
	 * @throws Exception
	 * @applicableTo Desktop, Mobile
	 * @description This method returns the text value of the WebElement specified by objName/obj                                                    
	 */		
	public String getElementText_By(By obj , String objName) throws Exception
	{
		try
		{			
			WebElement element=getElement(obj,objName);
			return element.getText().trim();		
		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method 'getElementText' : Failed to get the text of '"+objName + "' due to Exception : "+e);
			return null;
		}
	}
	
	/**
	 * This method checks whether the expected label is displayed for the element
	 * @Name verifyLabel
	 * @param element - WebElement element locator of web element
	 * @param strExpElementText - Expected value of text for the webElement
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @description This method checks the correct label displays for the element
	 * 				-Returns true if the expected text value matches with retrieved text value.
	 * 				-Returns true if the expected text value does not match with retrieved text value.
	 * @throws Exception
	 * @return  Boolean                                               
	 */
	public boolean verifyLabel(WebElement element ,String strExpElementText, String objName) throws Exception
	{
		try
		{			
			//Getting the Actual text of the element
			String strActElementText=element.getText().trim();

			if(strActElementText.equalsIgnoreCase(strExpElementText))
			{
				//objReport.setValidationMessageInReport("PASS", "Correct '"+strExpElementText+"' label  is displayed for '"+objName+"' element");			
				return true;
			}	
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "Incorrect '"+strExpElementText+"' label  is displayed for '"+objName+"' element");			
				return false;
			}	

		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyLabel' : Failed to verify the labbel of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}
	}
	
	/**
	 * This method checks whether the expected label is displayed for the element
	 * @Name verifyLabel
	 * @param obj -By element locator of web element
	 * @param strExpElementText - Expected value of text for the webElement
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @description This method checks the correct label displays for the element
	 * 				-Returns true if the expected text value matches with retrieved text value.
	 * 				-Returns true if the expected text value does not match with retrieved text value.
	 * @throws Exception
	 * @return  Boolean                                               
	 */
	public boolean verifyLabel_By(By obj ,String strExpElementText, String objName) throws Exception
	{
		try
		{			
			WebElement element=getElement(obj,objName);

			//Getting the Actual text of the element
			String strActElementText=element.getText().trim();

			if(strActElementText.equalsIgnoreCase(strExpElementText))
			{
				//objReport.setValidationMessageInReport("PASS", "Correct '"+strExpElementText+"' label  is displayed for '"+objName+"' element");			
				return true;
			}	
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "Incorrect '"+strExpElementText+"' label  is displayed for '"+objName+"' element");			
				return false;
			}	

		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyLabel' : Failed to verify the labbel of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}
	}
	
	/**
	 * This method returns the value of the html attribute(strAttribute)
	 * @Name getAttributeValue         
	 * @Applicable Desktop,Mobile
	 * @param element -  WebElement element locator of web element
	 * @param strAttribute - Name of the html Attribute
	 * @param objName - Name of the drop-down WebElement
	 * @return String
	 * @description This method returns the value of the html attribute(strAttribute) for the WebElement specified by obj/objName                                                         
	 */
	public String  getAttributeValue(WebElement element, String strAttribute,String objName) throws Exception
	{
		try
		{
			return element.getAttribute(strAttribute).trim();
		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method getAttributeValue : Failed to reteive '"+strAttribute+"' attribute value of the '"+objName+ "' element due to Exception : "+e);	
			return null;
		}
	}
	
	/**
	 * This method returns the value of the html attribute(strAttribute)
	 * @Name getAttributeValue         
	 * @Applicable Desktop,Mobile
	 * @param obj -  By element locator of web element
	 * @param strAttribute - Name of the html Attribute
	 * @param objName - Name of the drop-down WebElement
	 * @return String
	 * @description This method returns the value of the html attribute(strAttribute) for the WebElement specified by obj/objName                                                         
	 */
	public String  getAttributeValue_By(By obj, String strAttribute,String objName) throws Exception
	{
		try
		{
			return getElement(obj,objName).getAttribute(strAttribute).trim();
		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Method getAttributeValue : Failed to reteive '"+strAttribute+"' attribute value of the '"+objName+ "' element due to Exception : "+e);	
			return null;
		}
	}
	
	//Can be handled through previous method
	/**
	 * Validates the text available in the textbox element
	 * @Name verifyTextboxValue  
	 * @param Webelement of the locator to be searched  
	 * @param objName The Element Name to be searched
	 * @return 
	 * @applicableTo  Desktop,Mobile   
	 *                    
	 */
	public static String getTextBoxValue(WebElement element, String objName) {
		return element.getAttribute("value");
	}
	
	/**
	 * Validates the text available in the textbox element
	 * @Name verifyTextboxValue  
	 * @param objLocator The By class locator of the element to be searched  
	 * @param objName The Element Name to be searched
	 * @return 
	 * @applicableTo  Desktop,Mobile   
	 *                    
	 */
	public String getTextboxValue(By objLocator, String objName)
	{
		
		String actTextBoxVal = null;
		
		try 
		{
			getElement(objLocator, objName).sendKeys(Keys.CONTROL + "a");

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_C);

			Thread.sleep(3000);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();

			actTextBoxVal = (String) clipboard.getData(DataFlavor.stringFlavor);
			
			return actTextBoxVal;

		} catch (Exception e) 
		{
			//objReport.setValidationMessageInReport("FAIL", "Method verifyTextboxValue : Failed due to exception : "+e);
			return null;
		}
		
	}
	
	
	//Browser commands
	
	/**
	 * This method returns title of the webpage
	 * @return string value
	 */
	public static String getTitle(){
		
		return driver.getTitle();
		
	}
	
	/**
	 * This method returns current url of the App
	 * @return string value
	 */
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	/**
	 * This method is used to obtain the page source of the current page displayed and prints it on the console as well.
	 * @applicableTo  Mobile, desktop
	 * @return String
	 */	
	public String getAppPageSource(){
		String pageSource="";
		pageSource=driver.getPageSource();
		System.out.println(pageSource);
		return pageSource;
	}
	
	public static void navigateToApp() throws IOException {
		prop=BrowserDriverFactory.readConfig();	
		String appUrl = prop.getProperty("AppUrl");
		driver.navigate().to(appUrl);
	}
	
	public static void goBack() {
		driver.navigate().back();
	}
	
	public static void goForward() {
		driver.navigate().forward();
	}
	
	
	//isDisplayed, isSelected and isEnabled
	
	/**
	 * This method checks element visibility in the Web page or Mobile App  
	 * @Name verifyElementVisibility
	 * @param element - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element visibility in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementDisplay(WebElement element,String objName) throws Exception
	{
		try
		{			
			Boolean elementDisplayStatus=element.isDisplayed();

			if(elementDisplayStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyElementVisibility' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}
	}
	
	/**
	 * This method checks element visibility in the Web page or Mobile App  
	 * @Name verifyElementVisibility
	 * @param obj - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element visibility in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementDisplay_By(By obj,String objName) throws Exception
	{
		try
		{			
			Boolean elementDisplayStatus=getElement(obj,objName).isDisplayed();

			if(elementDisplayStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyElementVisibility' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}
	}
	
	/**
	 * This method checks element display status in the Web page or Mobile App  
	 * @Name getElementDisplayStatus
	 * @param obj - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks the size of the Web Element list and returns its count
	 * @return  Boolean                                               
	 */
	public boolean getElementDisplayStatus(By obj,String objName) throws Exception
	{
		try{
			int elementListSize=getElementCount(obj, objName);
			if(elementListSize!=0){
				return true;
			}
			else{
				return false;
			}
		}

		catch(NoSuchElementException e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'getElementDisplayStatus' : Failed to get count of '"+objName + "' element due to Exception '"+e+"'");
			return false;
		}
	}
		
	/**
	 * This method checks element selection status in the Web page or Mobile App  
	 * @Name verifyElementSelected
	 * @param element - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element selection status in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementSelected(WebElement element, String objName) 
	{			
		try
		{			
			Boolean elementSelectStatus=element.isSelected();

			if(elementSelectStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyElementSelected' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}		
		
	}
	
	/**
	 * This method checks element selection status in the Web page or Mobile App  
	 * @Name verifyElementSelected
	 * @param obj - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element selection status in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementSelected_By(By obj, String objName) 
	{			
		try
		{			
			Boolean elementSelectStatus=getElement(obj, objName).isSelected();

			if(elementSelectStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'verifyElementSelected' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}		
		
	}
	
	/**
	 * This method checks element enabled or not in the Web page or Mobile App  
	 * @Name verifyElementEnabled
	 * @param element - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element enable status in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementEnabled(WebElement element, String objName) 
	{			
		try
		{			
			Boolean elementEnabledStatus=element.isEnabled();

			if(elementEnabledStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'elementEnabledStatus' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}	
		
	}
	
	/**
	 * This method checks element enabled or not in the Web page or Mobile App  
	 * @Name verifyElementEnabled
	 * @param obj - WebElement locator class object 
	 * @param objName - Name of the WebElement
	 * @applicableTo  Desktop,Mobile
	 * @throws Exception
	 * @description This method checks element enable status in the Web page or Mobile App
	 * @return  Boolean true or false                                               
	 */
	public boolean verifyElementEnabled_By(By obj, String objName) 
	{			
		try
		{			
			Boolean elementEnabledStatus=getElement(obj, objName).isEnabled();

			if(elementEnabledStatus==true)
			{
				//objReport.setValidationMessageInReport("PASS", "'"+objName+"' is displayed");			
				return true;
			}		
			else
			{
				//objReport.setValidationMessageInReport("FAIL", "'"+objName+"' is not displayed");
				return false;
			}

		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method 'elementEnabledStatus' : Failed to verify visibility of '"+objName + "' element due to Exception '"+e+"'");			
			return false;
		}	
		
	}
	
	
	
	
	//Dropdown
	
	/**
	 * This method selects the value(strValueToBeSelected) in the drop-down specified by objName
	 * @Name selectDropdown         
	 * @param drpDownElement -  The WebElement of the dropdown
	 * @param strValueToBeSelected - Value to be selected in the drop-down
	 * @param objName - Name of the drop-down WebElement
	 * @description This method selects the value(strValueToBeSelected) in the drop-down specified by objName
	 * @Applicable Desktop,Mobile                                                              
	 */
	public void selectDropdownByVisibleText(WebElement drpDownElement, String strValueToBeSelected, String objName) throws Exception
	{
		try
		{
			Select selectListBox = new Select(drpDownElement);			
			selectListBox.selectByVisibleText(strValueToBeSelected);
			//objReport.setValidationMessageInReport("PASS", "'"+strValueToBeSelected+"' value is selected in '"+objName+"' drop-down");	
		}		
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method selectDropdown : Failed to select the '"+strValueToBeSelected+"' value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
	}
		
	public void selectDropdownByIndex(WebElement drpDownElement,int index, String objName)
	{
		try
		{
			Select selectListBox = new Select(drpDownElement);			
			selectListBox.selectByIndex(index);
			//objReport.setValidationMessageInReport("PASS", "'"+index+"' value is selected in '"+objName+"' drop-down");	
		}		
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method selectDropdown : Failed to select the '"+index+"' value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
	}
	
	public void selectDropdownByValue(WebElement drpDownElement,String value, String objName)
	{
		try
		{
			Select selectListBox = new Select(drpDownElement);			
			selectListBox.selectByValue(value);
			//objReport.setValidationMessageInReport("PASS", "'"+strValueToBeSelected+"' value is selected in '"+objName+"' drop-down");	
		}		
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method selectDropdown : Failed to select the '"+strValueToBeSelected+"' value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
	}
	
	/**
	 * This method selects the value(strValueToBeSelected) in the drop-down specified by objName
	 * @Name selectDropdown         
	 * @param obj -  The By class object of the dropdown
	 * @param strValueToBeSelected - Value to be selected in the drop-down
	 * @param objName - Name of the drop-down WebElement
	 * @description This method selects the value(strValueToBeSelected) in the drop-down specified by objName
	 * @Applicable Desktop,Mobile                                                              
	 */
	public void selectDropdownByVisibleText_By(By obj, String strValueToBeSelected, String objName) throws Exception
	{
		try
		{
			Select selectListBox = new Select(getElement(obj,objName));			
			selectListBox.selectByVisibleText(strValueToBeSelected);
			//objReport.setValidationMessageInReport("PASS", "'"+strValueToBeSelected+"' value is selected in '"+objName+"' drop-down");	
		}		
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method selectDropdown : Failed to select the '"+strValueToBeSelected+"' value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}

	}

	/**
	 * It returns the selected text value of drop-down element specified by objName
	 * @Name getDropdown         
	 * @param drpDownElement -  The WebElement of the dropdown
	 * @param objName - Name of the drop-down WebElement
	 * @description It returns the selected text value of drop-down element specified by objName
	 * @Applicable Desktop,Mobile 
	 * @return String                                                           
	 */
	public String getDrpdwnFirstSelectedValue(WebElement drpDownElement,String objName) throws Exception
	{
		String strText=null;
		try{                       
			Select selectListBox = new Select(drpDownElement);
			strText = selectListBox.getFirstSelectedOption().getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method getDrpdwnSeltdValue : Failed to retrieve the selected value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
		return strText;
	}
	
	/**
	 * It returns the selected text value of drop-down element specified by objName
	 * @Name getDropdown         
	 * @param obj -  The By class object of the dropdown
	 * @param objName - Name of the drop-down WebElement
	 * @description It returns the selected text value of drop-down element specified by objName
	 * @Applicable Desktop,Mobile 
	 * @return String                                                           
	 */
	public String getDrpdwnFirstSelectedValue_By(By obj,String objName) throws Exception
	{
		String strText=null;
		try{                       
			Select selectListBox = new Select(getElement(obj,objName));
			strText = selectListBox.getFirstSelectedOption().getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method getDrpdwnSeltdValue : Failed to retrieve the selected value of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
		return strText;
	}
	
	/**
	 * This method gets all the value of a drop-down specified by 'objName'
	 * @Name getAllDopdownValues         
	 * @param drpDownElements -  The WebElement of the dropdown
	 * @param objName - Name of the drop-down WebElement
	 * @return List<WebElement>
	 * @Applicable Desktop,Mobile
	 * @description This method gets all the value of a drop-down specified by 'objName'                                                           
	 */
	public List<WebElement> getAllDopdownValues(WebElement drpDownElements , String objName) throws Exception
	{
		List<WebElement> allOptions=null;
		try
		{		
			Select selectListBox = new Select(drpDownElements);			
			allOptions=selectListBox.getOptions();			
		}
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method getAllDopdownValues : Failed to reteive all  drop-down values of the of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
		return allOptions;
	}
	
	/**
	 * This method gets all the value of a drop-down specified by 'objName'
	 * @Name getAllDopdownValues         
	 * @param obj -  The By class object of the dropdown
	 * @param objName - Name of the drop-down WebElement
	 * @return List<WebElement>
	 * @Applicable Desktop,Mobile
	 * @description This method gets all the value of a drop-down specified by 'objName'                                                           
	 */
	public List<WebElement> getAllDropdownValues_By(By obj, String objName) throws Exception
	{
		List<WebElement> allOptions=null;
		try
		{
			//WebElement obj_Select=driver.findElement(obj);	
			WebElement elementSelect=getElement(obj, objName);
			Select selectListBox = new Select(elementSelect);			
			allOptions=selectListBox.getOptions();			
		}
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method getAllDopdownValues : Failed to reteive all  drop-down values of the of the '"+objName+ "' drop-down due to Exception : "+e);	
		}
		return allOptions;
	}
	
	public List<WebElement> getAllSelectedOptions(WebElement element, String objName){
		Select select=new Select(element);
		return select.getAllSelectedOptions();
	}
	
	public void deSelectByText(WebElement element,String text){
		Select select=new Select(element);
		select.deselectByVisibleText(text);
	}
	
	public void deSelectByIndex(WebElement element,int index){
		Select select=new Select(element);
		select.deselectByIndex(index);
	}
	
	public void deSelectByValue(WebElement element,String value){
		Select select=new Select(element);
		select.deselectByValue(value);
	}
	
	//Check box
	
	public void selectCheckBox(WebElement element, String objName) {
		if (!verifyElementSelected(element, objName))
			element.click();
	}
	public void selectCheckBox(By byobj, String objName) throws Exception {
		
		WebElement element = getElement(byobj, objName);
		
		if (!verifyElementSelected(element, objName))
			element.click();
	}

	public void deSelectCheckbox(WebElement element, String objName) {
		if (verifyElementSelected(element, objName))
			element.click();
	}

	//Radio button
	
	public void selectRadioButton(WebElement element, String objName) {
		if (!verifyElementSelected(element, objName))
			element.click();
	}

	public void deSelectRadioButton(WebElement element, String objName) {
		if (verifyElementSelected(element, objName))
			element.click();
	}
	
	
	//Alert
	
	public boolean isAlertPresent() 
	{ 
		try { 
			driver.switchTo().alert();
	        return true; 
	    }catch (NoAlertPresentException Ex) { 
	        return false; 
	     } 
	}
	
	/* I found catching exception of driver.switchTo().alert(); is so slow in Firefox (FF V20 & selenium-java-2.32.0).`
	So I choose another way:

	    private static boolean isDialogPresent(WebDriver driver) {
	        try {
	            driver.getTitle();
	            return false;
	        } catch (UnhandledAlertException e) {
	            // Modal dialog showed
	            return true;
	        }
	    }
	And it's a better way when most of your test cases is NO dialog present (throwing exception is expensive).*/
		
	public void acceptAlert()
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();	
	}
		
	public void dismissAlert()
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();	
	}
		
	public String getTextFromAlert()
	{
		Alert alert = driver.switchTo().alert();
		String actualAlertMsg = alert.getText();
		return actualAlertMsg;
	}
	
	public void enterTextOnAlertPrompt(String strText) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(strText);
		alert.accept();		
	}
	
	/*
		* long startTime = System.currentTimeMillis();
		* long stopTime = System.currentTimeMillis();
		  long elapsedTime = stopTime - startTime;
		 * 
		 * String executionTime = String.format("%d min, %d sec", 
	    TimeUnit.MILLISECONDS.toMinutes(elapsedTime),
	    TimeUnit.MILLISECONDS.toSeconds(elapsedTime) - 
	    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedTime)));
		*/
	
	
	//Frame 
	
	public void switchToFrameByWebELement(WebElement frameElement) {
		
		driver.switchTo().frame(frameElement);
		
		//using iframe WebElement
		//WebElement frameElement = driver.findElement(By.xpath("//*[@name='mainpanel']"));			
		//driver.switchTo().frame(frameElement);
		
	}
	
	public void switchToFrameByIdOrName(String nameOrId) {
		
		driver.switchTo().frame(nameOrId);
		
		//using name attribute of iframe
		//driver.switchTo().frame("mainpanel");
		
	}
	
	/**
	 * Select a frame by its (zero-based) index. Selecting a frame by index is equivalent to the JS expression window.frames[index] where "window" is the DOM window represented by the current context. Once the frame has been selected, all subsequent calls on the WebDriverinterface are made to that frame.
	 * @param index - index of the frame
	 */
	public void switchToFrameByIndex(int index) {
		
		driver.switchTo().frame(index);
		
		//using index of iframe - if its 2nd iframe then index = 1
		//driver.switchTo().frame(1);
		
	}
	
	public void switchToMainWindow() {
		driver.switchTo().defaultContent();
	}
	
	//Window handle
	
	public String getParentWindowId() {
		String parentWindowHandle = driver.getWindowHandle();
		return parentWindowHandle;
	}
	
	public Set<String> getAllWindowHandles() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		return allWindowHandles;
	}
	
	public void switchToChildWindow(String childWindowHandle) {
		driver.switchTo().window(childWindowHandle);
	}
	
	public void switchToParentWindow(String parentWindowHandle) {
		driver.switchTo().window(parentWindowHandle);
	}
	
	//Explicit Wait
	
	/** WebElement
	 * Script execution will wait till condition specified in 'strConditionMode' is completed 
	 * @Name explicitWait         
	 * @Applicable Desktop,Mobile
	 * @param obj - WebElement element locator of web element
	 * @param maxTimeOut - Maximum waiting time (Seconds)
	 * @param strConditionMode - Waiting condition( Value : VISIBILITY , INVISIBILITY, PRESENCE , FRAME, CLICKABLE)
	 * @description Script execution will wait(Maximum time specified in maxTimeOut) till condition specified in 'strConditionMode' is completed                                                            
	 */
	public void explicitWait(WebElement element, int maxTimeOut, String strConditionMode) throws Exception
	{
		try{

			String mode = strConditionMode.toUpperCase();                    
			switch (mode) {
			case "VISIBILITY":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.visibilityOf(element));       
				break;

			case "INVISIBILITY":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.invisibilityOf(element));
				break;

			/*case "PRESENCE":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.presenceOfElementLocated(obj));
				break;*/

			case "FRAME":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
				break; 

			case "CLICKABLE":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.elementToBeClickable(element));        
				break;
			default:
				//objReport.setValidationMessageInReport("FAIL", "Method explicitWait: Incorrect checking condition mode is '"+strConditionMode+"' provided. Please provide the correct condition mode");	

			}				
		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method explicitWait: Exception '"+e+"' encountered");	
		}

	}
	
	/**BY
	 * Script execution will wait till condition specified in 'strConditionMode' is completed 
	 * @Name explicitWait         
	 * @Applicable Desktop,Mobile
	 * @param obj - By element locator of web element
	 * @param maxTimeOut - Maximum waiting time (Seconds)
	 * @param strConditionMode - Waiting condition( Value : VISIBILITY , INVISIBILITY, PRESENCE , FRAME, CLICKABLE)
	 * @description Script execution will wait(Maximum time specified in maxTimeOut) till condition specified in 'strConditionMode' is completed                                                            
	 */
	public void explicitWait_By(By obj,int maxTimeOut , String strConditionMode) throws Exception
	{
		try{

			String mode = strConditionMode.toUpperCase();                    
			switch (mode) {
			case "VISIBILITY":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.visibilityOfElementLocated(obj));       
				break;

			case "INVISIBILITY":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.invisibilityOfElementLocated(obj));
				break;

			case "PRESENCE":
				
				/*WebDriverWait wait=new WebDriverWait(driver,40);
				WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(obj));*/
				
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.presenceOfElementLocated(obj));
				break;   

			case "FRAME":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(obj));
				break; 

			case "CLICKABLE":
				(new WebDriverWait(driver, maxTimeOut))
				.until(ExpectedConditions.elementToBeClickable(obj));        
				break;
			default:
				//objReport.setValidationMessageInReport("FAIL", "Method explicitWait: Incorrect checking condition mode is '"+strConditionMode+"' provided. Please provide the correct condition mode");	

			}				
		}
		catch(Exception e){
			//objReport.setValidationMessageInReport("FAIL", "Method explicitWait: Exception '"+e+"' encountered");	
		}

	}



	//JavascriptExecutor
	
	public static void ScrollIntoViewElement(WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;		 
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		  
	}
	
	public static void ScrollDown() {
				 
		JavascriptExecutor js = (JavascriptExecutor) driver;		 
		//js.executeScript("scroll(0,800)");
		js.executeScript("scroll(0,document.body.scrollHeight)");
		
	}
	
	public static void ScrollUp() {
				 
		JavascriptExecutor js = (JavascriptExecutor) driver;		 
		//js.executeScript("scroll(0,-800)");
		js.executeScript("scroll(0,-document.body.scrollHeight)");
		
	}
	
	//id of the element
	public void enterTextByJS(String id, String strText) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		//js.executeScript("document.getElementById('nav-search-query').value='java script tutorial';");
		js.executeScript("document.getElementById('"+id+"').value='"+strText+"';");
	
	}
	
	public void enterTextByJS(WebElement element, String strText) {
			
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			
			//js.executeScript("document.getElementById('nav-search-query').value='java script tutorial';");
			js.executeScript("arguments[0].value='"+strText+"';", element);
		
	}
	
	//id of the element
	public void clickElementByJS(String id) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		//js.executeScript("document.getElementById('login').click();");
		js.executeScript("document.getElementById('"+id+"').click();");
		
	}
	
	public void clickElementByJS(WebElement element) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		//js.executeScript("document.getElementById('login').click();");
		js.executeScript("arguments[0].click();", element);
		
	}
	
	//id of the element
	public static void selectCheckBoxByJS(String id) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		//js.executeScript("document.getElementById('remember').checked=true;");
		
		js.executeScript("document.getElementById('"+id+"').checked=true;");
		
	}
	
	//id of the element
	public static void deselectCheckBoxById_JS(String id) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		//js.executeScript("document.getElementById('remember').checked=false;");
		
		js.executeScript("document.getElementById('"+id+"').checked=false;");
		
	}
	
	public void highlightElement(WebElement element) {
		
		//JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		String bgColor = element.getCssValue("backgroundColor");
		
		for(int i = 0; i < 10; i++) {	
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}		
	}
	
	public static void changeColor(String color, WebElement element) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void drawBorderOnElement(WebElement element) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		js.executeScript("arguments[0].style.border = '3px solid red'", element);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public void refreshBrowserByJs() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
			
		//js.executeScript("document.getElementById('login').click();");
		js.executeScript("history.go(0);");			
		
	}
	
	/**
	 * This method will refresh the web application 
	 * @Name pageReload
	 * @applicableTo  Desktop,Mobile
	 * @description  This method will reload the webpage                                           
	 */
	public void pageReload() throws Exception
	{
		try {
			driver.switchTo().defaultContent();
			((JavascriptExecutor) driver).executeScript("location.reload();");

		}catch(Exception e)
		{
			//objReport.setValidationMessageInReport("FAIL", "Method 'pageReload' : Failed to relaod the web page due to Exception '"+e+"'");			  
		}

	}
	
	
	public String getTitleByJS() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		String title = js.executeScript("return document.title;").toString();
		
		return title;
	}
	
	public String getInnerTextByJS() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		
		return pageText;
		
	}
		
	public void generateAlert(String strText) {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("alert('"+strText+"')");
	}
	
	public void ZoomInBypercentage() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);		
		js.executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);		
		js.executeScript("document.body.style.zoom='100%'");
	}
	
	
	//Keyboard and Mouse Actions
	
	/**
	 * Hover over the WebElement specified in objName
	 * @Name hoverElement         
	 * @param obj -  The By class object of the web element
	 * @param objName - Name of the WebElement
	 * @Applicable Desktop
	 * @description This method hovers the mouse pointer on WebElement specified                                                  
	 */
	public void hoverElement(By obj,String objName) throws Exception
	{
		try {
			Actions action= new Actions(driver);
			WebElement hoverElement=getElement(obj,objName);
			action.moveToElement(hoverElement).build().perform();		
			//objReport.setValidationMessageInReport("Hover over the "+objName,"", "", "PASS");
			//objReport.setValidationMessageInReport("PASS", "Hover over the '"+objName+ "' element");	
		}
		catch(Exception e) {
			//objReport.setValidationMessageInReport("FAIL", "Failed to Hover over the '"+objName+ "' element due to Exception : "+e );
		}
	}

	/**
	 * Double clicks the element(Button,Link,Image)
	 * @Name double click
	 * @param objLocator The By class object of the element to be clicked  
	 * @param objName The Element Name to be clicked    
	 * @applicableTo  Desktop,Mobile   
	 * @description double Clicks the element(Button,Link,Image)                              
	 */
	public void doubleclick(By elementLocator,String elementName) 
	{
		try {
			Actions action= new Actions(driver);
			WebElement hoverElement=getElement(elementLocator,elementName);
			action.moveToElement(hoverElement).doubleClick().perform();
			//objReport.setValidationMessageInReport("PASS", elementName + " is clicked");

		}
		catch(Exception e) {
			e.printStackTrace();
			//objReport.setValidationMessageInReport("FAIL", "Method doubleclick : Failed due to Exception : "+e);
		}

	}	
	


	
	//Screenshots methods
	public static String takeScreenshots(String fileName) {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/Screenshots/" + fileName+"_"+System.currentTimeMillis() + ".png";
		
		//String path = ".//Screenshots//"+CommonUtilities.getFileName(fileName)+".png";
		
		//FileUtils.copyFile(srcFile, destFile);
		try {
			//FileUtils.copyFile(srcFile, new File(".//Screenshots//"+CommonUtilities.getFileName(fileName)+".png"));
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return path;
		
	}
	


	

}
