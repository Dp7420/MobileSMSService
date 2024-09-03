package com.app.otp.mobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.otp.mobile.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
	Optional<Otp> findByUserIdAndOtpValue(Long userId,String otpValue);
}
