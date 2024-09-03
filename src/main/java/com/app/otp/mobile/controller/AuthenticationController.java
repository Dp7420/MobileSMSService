package com.app.otp.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.otp.mobile.request.SigninRequest;
import com.app.otp.mobile.request.SignupRequest;
import com.app.otp.mobile.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {    
	
    @Autowired
    private UserService userService;

//    https://localhost:8888/api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        return userService.signin(request);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobileNum, @RequestParam String otpValue) {
    	return ResponseEntity.ok(userService.verifyOtp(mobileNum, otpValue));
    }

}
