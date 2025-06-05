package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.modals.request.LoginRequest;
import com.api.modals.response.LoginResponse;
import com.api.modals.response.UserProfileResponse;

import io.restassured.response.Response;

public class GetProfileRequestsTest {
	@Test(description = "Verify if get profile is working...")
	public void getProfileInfoTest() {
		
	// first get Token by login
	AuthService authService = new AuthService();
	Response response = authService.login(new LoginRequest("uday123", "uday123"));
	LoginResponse loginResponse = response.as(LoginResponse.class);
	System.out.println(loginResponse.getToken());
	
	// use Token for viewing profile after login
	UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
	response = userProfileManagementService.getProfile(loginResponse.getToken());
	UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
	System.out.println(userProfileResponse.getUsername());
	}
}
