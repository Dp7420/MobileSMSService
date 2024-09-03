package com.app.otp.mobile.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.otp.mobile.entity.Otp;
import com.app.otp.mobile.repository.OtpRepository;

@Service
public class OtpService {
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private smsService smsService; 

	public Otp generateOtp(Long userId,String mobileNum) {
		String otpValue = generateOtpvalue();
		Otp otp = new Otp();
		otp.setUserId(userId);
		otp.setOtpValue(otpValue);
		otp.setSendDateTime(Timestamp.from(Instant.now()));
		otp.setExpiredTime(2);
		otp.setStatus(0);
		otp.setCreatedBy(1001l);
		otp.setUpdatedBy(1001l);
		otp.setCreatedDate(Timestamp.from(Instant.now()));
		otp.setUpdatedDate(Timestamp.from(Instant.now()));

		otpRepository.save(otp);
		smsService.sendSms(mobileNum,otpValue);
		return otp;
		
	}

	public Optional<Otp> findOtp(Long userId, String otpvalue) {
		return otpRepository.findByUserIdAndOtpValue(userId, otpvalue);
	}

	public void updateOtpStatus(Otp otp, int status) {
		otp.setStatus(status);
		otp.setUpdatedDate(Timestamp.from(Instant.now()));
		otpRepository.save(otp);
	}

	public String generateOtpvalue() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(999999);
		return String.valueOf(otp);
	}
}
