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
@Table(name = "TBL_OTP")
public class Otp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "send_date_time")
	private Timestamp sendDateTime;
	
	@Column(name = "expired_time")
	private int expiredTime;
	
	@Column(name = "otp_value", nullable = false)
	private String otpValue;
	
	@Column(name = "status", nullable = false)
	private int status;
	
	@Column(name = "createdBy")
	private Long createdBy;
	
	@Column(name = "updatedBy")
	private Long updatedBy;
	
	@Column(name = "createdDate")
	private Timestamp createdDate;
	
	@Column(name = "updatedDate")
	private Timestamp updatedDate;
}
