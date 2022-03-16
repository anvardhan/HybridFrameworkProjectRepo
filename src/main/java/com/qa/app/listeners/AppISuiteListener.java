package com.qa.app.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class AppISuiteListener implements ISuiteListener {
	
	public void onStart(ISuite suite) {	
		System.out.println("AppISuiteListener --> onStart function started "  + suite.getName());
	}
	public void onFinish(ISuite suite) {
		System.out.println("AppISuiteListener --> onFinish function started "  + suite.getName());
	}

}
