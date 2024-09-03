package com.app.otp.mobile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsMantraConfig {

    @Value("${sms.url}")
    private String apiUrl;

    @Value("${sms.senderid}")
    private String senderId;

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.clientid}")
    private String clientId;

    public String getApiUrl() {
        return apiUrl;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getClientId() {
        return clientId;
    }
}
