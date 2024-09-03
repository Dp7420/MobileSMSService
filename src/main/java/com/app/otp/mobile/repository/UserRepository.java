package com.app.otp.mobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.otp.mobile.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByMobileNum(String mobileNum);
	
	Boolean existsByMobileNum(String mobileNum);
	
	Boolean existsByEmail(String email);
}
