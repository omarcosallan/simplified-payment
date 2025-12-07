package com.marcos.simplified_payment.service;

import com.marcos.simplified_payment.dto.notification.NotificationRequestDTO;
import com.marcos.simplified_payment.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RestTemplate restTemplate;

    @Value("${app.authorization.api.notification}")
    private String authApiUrl;

    public void send(String email, String message) {
        try {
            NotificationRequestDTO body = new NotificationRequestDTO(email, message);
            ResponseEntity<Map> authorizationResponse = restTemplate.postForEntity(authApiUrl, body, Map.class);
            if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
                log.info("Notification sent successfully");
            }
        } catch (Exception e) {
            log.info("Notification sent failed");
        }
    }
}
