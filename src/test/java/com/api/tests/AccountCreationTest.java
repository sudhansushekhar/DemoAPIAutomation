package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.modals.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {

	@Test(description = "Verify if account creation is working...")
	public void createAccountTest() {
		SignUpRequest signUpRequest = new SignUpRequest.Builder()
		.username("sud225")
		.email("sud129@gmail.com")
		.password("sud4296")
		.firstName("Sudhanshu")
		.lastName("Shekhar")
		.mobileNumber("9897865675")
		.build();
		
		AuthService authService = new AuthService();
		Response response = authService.signUp(signUpRequest);
		
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
		Assert.assertEquals(response.statusCode(), 200);
	}
}
