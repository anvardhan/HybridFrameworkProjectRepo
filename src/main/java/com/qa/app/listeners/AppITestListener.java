package com.qa.app.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.app.utils.WebElementUtil;

public class AppITestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("AppITestListener -> onTestStart -> "+ result.getName() + " Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("AppITestListener -> onTestSuccess -> "+ result.getName() + " Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//System.out.println("AppITestListener -> -> "+result.isSuccess());
		
		WebElementUtil.takeScreenshots(result.getName());
		
		System.out.println("AppITestListener -> onTestFailure -> "+result.getClass().getName()+" failed");
		System.out.println("Screenshots taken!!");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("AppITestListener -> onTestSkipped -> "+ result.getName() + " Skipped");		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		
	}

}
