package com.thummimjung.expenseservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class MachineLearningService {

    private static final String ML_API_URL = "http://localhost:5000/predict";

    public Map<String, Object> getPrediction(Double amount) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Double> request = new HashMap<>();
        request.put("amount", amount);
        ResponseEntity<Map> response = restTemplate.postForEntity(ML_API_URL, request, Map.class);
        return response.getBody();
    }
}
