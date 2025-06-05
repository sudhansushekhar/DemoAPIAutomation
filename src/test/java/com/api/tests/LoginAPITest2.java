package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class LoginAPITest2 {
	
	@Test(description = "Verify if login is working...")
	public void loginTest() {
		String baseURI = "http://64.227.160.186:8080";
		Response response = given()
				.baseUri(baseURI)
				.header("Content-Type", "application/json")
				.body("{\"username\": \"uday123\",\"password\": \"uday123\"}")
				.post("/api/auth/login");
		
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
