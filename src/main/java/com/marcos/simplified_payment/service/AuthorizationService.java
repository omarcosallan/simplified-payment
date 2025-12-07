package com.marcos.simplified_payment.service;

import com.marcos.simplified_payment.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final RestTemplate restTemplate;

    @Value("${app.authorization.api.transfer}")
    private String authApiUrl;

    public boolean authorize(Transfer transfer) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(authApiUrl, Map.class);
        return authorizationResponse.getStatusCode() == HttpStatus.OK;
    }
}
