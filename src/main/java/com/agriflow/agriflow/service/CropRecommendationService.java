package com.agriflow.agriflow.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CropRecommendationService {

    private final RestClient restClient;

    public CropRecommendationService() {

        this.restClient = RestClient.builder()
                .baseUrl("http://127.0.0.1:8000")
                .build();
    }

    public Map<String, Object> recommendCrop(
            double temperature,
            double humidity,
            double rainfall,
            String soilType) {

        return restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/recommend-crop")
                        .queryParam("temperature", temperature)
                        .queryParam("humidity", humidity)
                        .queryParam("rainfall", rainfall)
                        .queryParam("soil_type", soilType)
                        .build())
                .retrieve()
                .body(Map.class);
    }
}