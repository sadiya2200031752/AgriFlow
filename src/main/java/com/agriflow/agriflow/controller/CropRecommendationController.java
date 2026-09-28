package com.agriflow.agriflow.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.service.CropRecommendationService;

@RestController
public class CropRecommendationController {

    private final CropRecommendationService cropRecommendationService;

    public CropRecommendationController(
            CropRecommendationService cropRecommendationService) {

        this.cropRecommendationService = cropRecommendationService;
    }

    @GetMapping("/recommend-crop")
    public Map<String, Object> recommendCrop(
            @RequestParam double temperature,
            @RequestParam double humidity,
            @RequestParam double rainfall,
            @RequestParam String soilType) {

        return cropRecommendationService.recommendCrop(
                temperature,
                humidity,
                rainfall,
                soilType);
    }
}