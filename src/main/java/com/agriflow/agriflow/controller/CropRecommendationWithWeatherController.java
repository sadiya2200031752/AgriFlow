package com.agriflow.agriflow.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.service.CropRecommendationWithWeatherService;

@RestController
public class CropRecommendationWithWeatherController {

    private final CropRecommendationWithWeatherService service;

    public CropRecommendationWithWeatherController(
            CropRecommendationWithWeatherService service) {

        this.service = service;
    }

    @GetMapping("/recommend-crop-with-weather")
    public Map<String, Object> recommendCrop(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String soilType) {

        return service.recommendCrop(
                latitude,
                longitude,
                soilType);
    }
}