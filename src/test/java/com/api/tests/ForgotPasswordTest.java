package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import io.restassured.response.Response;

public class ForgotPasswordTest {

	@Test(description = "Verify if forgot password is working...")
	public void forgotPasswordTest() {
		
		AuthService authService = new AuthService();
		Response response = authService.forgotPassword("sud129@gmail.com");
		
		String responseBody = response.asPrettyString();
		System.out.println(responseBody);
		
		if (responseBody.contains("If your email exists in our system, you will receive reset instructions.")) {
		    System.out.println("PASS: Expected message found.");
		} else {
		    System.out.println("FAIL: Expected message NOT found.");
		}
	}
}
