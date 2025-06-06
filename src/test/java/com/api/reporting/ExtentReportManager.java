package com.api.reporting;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static ExtentReports createInstance() {
		if(extent == null) {
			String filename = generateReportFileName();
			new File("test-output/reports").mkdirs();  // Ensure folder exists
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/reports/" + filename);
			
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("API Test Report");
			sparkReporter.config().setReportName("REST API Test Results");
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Environment", "Test");
			extent.setSystemInfo("User", System.getProperty("user.name"));
			
		}
		return extent;
	}
	
	private static String generateReportFileName() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		return "APITestReport_" + dtf.format(LocalDateTime.now()) + ".html";
	}
	
	public static void startTest(String testName) {
		ExtentTest extentTest = createInstance().createTest(testName);
		test.set(extentTest);
	}
	
	public static void logRequest(FilterableRequestSpecification requestSpec) {
		StringBuilder requestDetails = new StringBuilder();
		requestDetails.append("<pre>");
		requestDetails.append("Request Method: ").append(requestSpec.getMethod()).append("\n");
		requestDetails.append("Request URI: ").append(requestSpec.getURI()).append("\n");
		requestDetails.append("Request Headers: ").append("\n");
		
		for(Header header : requestSpec.getHeaders()) {
			requestDetails.append("   ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
		}
		
		if(requestSpec.getBody() != null) {
			requestDetails.append("Request Body: ").append("\n");
			requestDetails.append(requestSpec.getBody().toString());
		}
		requestDetails.append("</pre>");
		
		test.get().log(Status.INFO, "Request Details: " + requestDetails.toString());
	}
	
	public static void logResponse(Response response) {
		StringBuilder responseDetails = new StringBuilder();
		responseDetails.append("<pre>");
		responseDetails.append("Response Status: ").append(response.getStatusCode()).append("\n");
		responseDetails.append("Response Headers: ").append("\n");
		
		response.getHeaders().forEach(header -> 
		responseDetails.append("   ").append(header.getName()).append(": ").append(header.getValue()).append("\n"));
		
		responseDetails.append("Response Body: ").append("\n");
		responseDetails.append(response.getBody().prettyPrint());
		responseDetails.append("</pre>");
		
		test.get().log(Status.INFO, "Response Details: " + responseDetails.toString());
		
		// Log status based on response code
		if(response.getStatusCode() >= 200 && response.getStatusCode() <300) {
			test.get().pass("Request Completed Successfully!!");
		}else {
			test.get().fail("Request Failed with Status Code: " + response.getStatusCode());
		}
	}
	
	public static void endTest() {
		if(test.get() != null) {
			extent.flush();
		}
	}
	
	public static ExtentTest getTest() {
		return test.get();
	}
}