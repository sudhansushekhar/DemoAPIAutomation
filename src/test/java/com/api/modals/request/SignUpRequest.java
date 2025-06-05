package com.api.modals.request;

public class SignUpRequest {
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	
	public SignUpRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public SignUpRequest(String username, String password, String email, String firstName, String lastName,
			String mobileNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public String toString() {
		return "SignUpRequest [username=" + username + ", password=" + password + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + "]";
	}
	
	// Builder design pattern - create an inner class , put instance variable in inner class
	// For each instance variable create methods, set the instance variable and that will return current object
	public static class Builder {
		private String username;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private String mobileNumber;
		
		public Builder username(String username) {
			this.username= username;
			//Builder x = new Builder(); // creating object - class will be loaded into memory, instance variable will be created, constructor will be called (we don't want this, we want object)
			Builder x = this; // will give current object 
			return x;
		}
		// in other way we can write like this
		public Builder password(String password) {
			this.password= password;
			return this; //current object
		}
		public Builder email(String email) {
			this.email= email;
			return this; //current object
		}
		public Builder firstName(String firstName) {
			this.firstName= firstName;
			return this; //current object
		}
		public Builder lastName(String lastName) {
			this.lastName= lastName;
			return this;
		}
		public Builder mobileNumber(String mobileNumber) {
			this.mobileNumber= mobileNumber;
			return this; //current object
		}
		
		public SignUpRequest build() {
			SignUpRequest signUpRequest = new SignUpRequest(username, password, email, firstName, lastName, mobileNumber);
			return signUpRequest;
		}
	}
}
