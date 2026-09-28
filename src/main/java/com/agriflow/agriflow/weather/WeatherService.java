package com.agriflow.agriflow.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(double latitude, double longitude) {

        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&current=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m"
                + "&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation"
                + "&timezone=Asia/Kolkata";

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}