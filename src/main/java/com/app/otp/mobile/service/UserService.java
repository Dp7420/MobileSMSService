package com.app.otp.mobile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.otp.mobile.entity.Otp;
import com.app.otp.mobile.entity.User;
import com.app.otp.mobile.repository.UserRepository;
import com.app.otp.mobile.request.SigninRequest;
import com.app.otp.mobile.request.SignupRequest;
import com.app.otp.mobile.response.MessageResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private OtpService otpService;
    
//    @Autowired
//    private OtpRepository otpRepository;
    
    public ResponseEntity<?> signup(SignupRequest request) {
//        String formattedMobileNum = formatMobileNumber(request.getMobileNum());

        if (repository.existsByMobileNum(request.getMobileNum())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User Already Exists With Mobile"));
        }
        if (repository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User Already Exists With Email"));
        }

        User user = new User();
        user.setAddress(request.getAddress());
        user.setDisplayName(request.getDisplayName());
        user.setCreatedBy(request.getCreatedBy());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMobileNum(request.getMobileNum());
        user.setProfile_pic(request.getProfile_pic());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());
        user.setUpdatedBy(request.getUpdatedBy());

        repository.save(user);
        return ResponseEntity.ok(new MessageResponse("SignUp successfully."));
    }

    public ResponseEntity<?> signin(SigninRequest request) {
//        String formattedMobileNum = formatMobileNumber(request.getMobileNum());
        Optional<User> user = repository.findByMobileNum(request.getMobileNum());
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User Not Found"));
        }
        otpService.generateOtp(user.get().getId(), request.getMobileNum());
        System.out.println( otpService.generateOtp(user.get().getId(), request.getMobileNum()));

        return ResponseEntity.ok("OTP Sent to Registered Mobile Number.");
    }
    
    
    public ResponseEntity<?> verifyOtp(String mobileNum,String otpValue){
    	String formattedNumber=formatMobileNumber(mobileNum);
    	Optional<User> user=repository.findByMobileNum(formattedNumber);
    	if(!user.isPresent()) {
    		return ResponseEntity.badRequest().body(new MessageResponse("User Not Found with this mobile number"));
    	}
    	Optional<Otp> otp=otpService.findOtp(user.get().getId(), otpValue);
    	if(!otp.isPresent() || otp.get().getStatus() !=0) {
    		return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired otp"));
    	}
    	
    	otpService.updateOtpStatus(otp.get(), 1);
    	return ResponseEntity.ok("Login Successfully.");
    }
    
    private String formatMobileNumber(String mobileNum) {
    	 if (mobileNum == null || mobileNum.isEmpty()) {
             throw new IllegalArgumentException("Mobile number must not be null or blank");
         }

         // Remove any non-digit characters
         mobileNum = mobileNum.replaceAll("\\D", "");

         // Remove leading zeros in the mobile number
         mobileNum = mobileNum.replaceFirst("^0+(?!$)", "");

//         if (countryCode == null || countryCode.isEmpty()) {
//             countryCode = "91"; // Default to 91 if countryCode is not provided
//         }
 //
//         // Ensure the country code starts with '+'
//         if (!countryCode.startsWith("+")) {
//             countryCode = "+" + countryCode;
//         }

//         // Check if mobile number already includes the country code
//         if (!mobileNum.startsWith(countryCode)) {
//             mobileNum = countryCode + mobileNum;
//         }

         return mobileNum;
     }
}
