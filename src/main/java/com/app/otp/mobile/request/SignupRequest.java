package com.app.otp.mobile.request;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class SignupRequest {

	private String mobileNum;

	private String firstName;
	
	private String lastName;
	
	private String displayName;
	
	private String profile_pic;
	
	private String email;
	
	private Timestamp dob;
	
	private String address;
	
	private String role;
	
	private int status;

	private Long createdBy;
	
	private Long updatedBy;
	
	private String countryCode;
}
