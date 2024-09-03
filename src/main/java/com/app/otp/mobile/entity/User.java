package com.app.otp.mobile.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_USER" )
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MOBILE_NO", nullable = false)
	private String mobileNum;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "PROFILE_PIC")
	private String profile_pic;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private Timestamp dob;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "Status", nullable = false)
	private int status;
	
	@Column(name = "createdBy")
	private Long createdBy;
	
	@Column(name = "updatedBy")
	private Long updatedBy;
}

