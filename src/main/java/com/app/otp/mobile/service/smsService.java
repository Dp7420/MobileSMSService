package com.app.otp.mobile.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class smsService {
    private static final String API_URL = "https://api.mylogin.co.in/api/v2/SendSMS";
    private static final String SENDER_ID = "ORCHSP";
    private static final String API_KEY = "aUZx/TDiBr4P7RPtdF5BjtbsRaHrdAnzA9SQj1c5os4=";
    private static final String CLIENT_ID = "dc579432-8cf9-4bbe-bcdb-300048220f19";

    public void sendSms(String mobileNumber, String message) {
        String formattedNumber = formatMobileNumber(mobileNumber);
        String encodedMessage = encodeMessage(message);
        String requestUrl = String.format(
            "%s?SenderId=%s&MobileNumbers=%s&ApiKey=%s&ClientId=%s&Message=%s",
            API_URL, SENDER_ID, formattedNumber, API_KEY, CLIENT_ID, encodedMessage
        );

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(requestUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Response: " + response.getBody());
//                logResponse(response.getBody());
            } else {
                System.err.println("Failed to send SMS: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Exception occurred while sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String formatMobileNumber(String mobileNumber) {
        if (!mobileNumber.startsWith("91")) {
            return "91" + mobileNumber;
        }
        return mobileNumber;
    }

    private String encodeMessage(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode message", e);
        }
    }

    private void logResponse(String response) {
        System.out.println("Full Response: " + response);
        // Add additional parsing and logging if necessary
    }
}
