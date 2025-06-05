package com.api.Listeners;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListeners.class);
	
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started!!");
	  }
	public void onTestStart(ITestResult result) {
		logger.info("Started!!" + result.getMethod().getMethodName());
		logger.info("Description!!" + result.getMethod().getDescription());
	  }
	public void onTestSuccess(ITestResult result) {
		logger.info("Passed!!" + result.getMethod().getMethodName());
	  }
	public void onTestFailure(ITestResult result) {
		logger.error ("Failed!!" + result.getMethod().getMethodName());
	  }
	public void onTestSkipped(ITestResult result) {
		logger.info("Skipped!!" + result.getMethod().getMethodName());
	  }
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed!!");
	  }
}
