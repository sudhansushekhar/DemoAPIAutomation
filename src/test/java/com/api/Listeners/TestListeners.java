package com.api.Listeners;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.reporting.ExtentReportManager;
import com.aventstack.extentreports.Status;

public class TestListeners implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListeners.class);
	
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started!!");
	  }
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String description = result.getMethod().getDescription();

		logger.info("Started!! " + testName);
		logger.info("Description: " + description);

		ExtentReportManager.startTest(testName);
		if (description != null) {
			ExtentReportManager.getTest().log(Status.INFO, "Test Description: " + description);
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		logger.info("Passed!!" + result.getMethod().getMethodName());
		ExtentReportManager.getTest().log(Status.PASS, "Test Passed Successfully");
		ExtentReportManager.endTest();
	  }
	
	public void onTestFailure(ITestResult result) {
		logger.error ("Failed!!" + result.getMethod().getMethodName());
		ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
		ExtentReportManager.endTest();
	  }
	
	public void onTestSkipped(ITestResult result) {
		logger.info("Skipped!!" + result.getMethod().getMethodName());
		ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
		ExtentReportManager.endTest();
	  }
	
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed!!");
	  }
}
