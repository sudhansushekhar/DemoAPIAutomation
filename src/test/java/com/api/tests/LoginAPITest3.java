package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.modals.request.LoginRequest;
import com.api.modals.response.LoginResponse;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

@Listeners(com.api.Listeners.TestListeners.class)
public class LoginAPITest3 {
	
	@Test(description = "Verify if login is working...")
	public void loginTest() {
		LoginRequest loginRequest = new LoginRequest("uday123", "uday123");
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		
		LoginResponse loginResponse = response.as(LoginResponse.class);
		
		System.out.println(response.asPrettyString());
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getType());
		System.out.println(loginResponse.getId());
		System.out.println(loginResponse.getUsername());
		System.out.println(loginResponse.getEmail());
		System.out.println(loginResponse.getRoles());
		
		//Assertions
		Assert.assertTrue(loginResponse.getToken() != null);
		Assert.assertEquals(loginResponse.getEmail(),"uday12345@gmail.com");
		Assert.assertEquals(loginResponse.getUsername(),"uday123");
		Assert.assertEquals(loginResponse.getId(),305);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
