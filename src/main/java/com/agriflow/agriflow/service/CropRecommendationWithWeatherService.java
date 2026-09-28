package com.agriflow.agriflow.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.weather.WeatherResponse;
import com.agriflow.agriflow.weather.WeatherService;

@Service
public class CropRecommendationWithWeatherService {

    private final WeatherService weatherService;
    private final CropRecommendationService cropRecommendationService;

    public CropRecommendationWithWeatherService(
            WeatherService weatherService,
            CropRecommendationService cropRecommendationService) {

        this.weatherService = weatherService;
        this.cropRecommendationService = cropRecommendationService;
    }

    public Map<String, Object> recommendCrop(
            double latitude,
            double longitude,
            String soilType) {

        // Get weather directly from WeatherService
        WeatherResponse weather =
                weatherService.getWeather(latitude, longitude);

        double temperature =
                weather.getCurrent().getTemperature_2m();

        double humidity =
                weather.getCurrent().getRelative_humidity_2m();

        double rainfall =
                weather.getCurrent().getPrecipitation();

        // Send weather conditions to ML model
        return cropRecommendationService.recommendCrop(
                temperature,
                humidity,
                rainfall,
                soilType);
    }
}