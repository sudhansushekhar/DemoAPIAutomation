package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.modals.request.LoginRequest;
import com.api.modals.request.ProfileRequest;
import com.api.modals.response.LoginResponse;
import com.api.modals.response.UserProfileResponse;

import io.restassured.response.Response;

public class UpdateProfileTest {
	@Test(description = "Verify if update profile is working...")
	public void updateProfileInfoTest() {
		
		// Get token first
		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("uday123", "uday123"));
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(response.asPrettyString());
		System.out.println("-----------Login Response----------");
		
		// get user profile info
		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		
		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		Assert.assertEquals(userProfileResponse.getUsername(), "uday123");
		
		System.out.println("------------------------------------------------");
		
		// create payload
		ProfileRequest profileRequest = new ProfileRequest.Builder()
				.firstName("uday12345")
				.lastName("uday12345").
				email("uday12345@gmail.com").
				mobileNumber("8767646543").
				build();
		
		// update user profile info
		response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
		System.out.println(response.asPrettyString());
	}
}
