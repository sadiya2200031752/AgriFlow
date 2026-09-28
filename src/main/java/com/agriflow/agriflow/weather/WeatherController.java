package com.agriflow.agriflow.weather;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherInsightService weatherInsightService;

    public WeatherController(
            WeatherService weatherService,
            WeatherInsightService weatherInsightService) {

        this.weatherService = weatherService;
        this.weatherInsightService = weatherInsightService;
    }

    @GetMapping("/weather")
    public WeatherInsightResponse getWeather(
            @RequestParam double latitude,
            @RequestParam double longitude) {

        WeatherResponse weather =
                weatherService.getWeather(latitude, longitude);

        double maximumTemperature =
                weatherInsightService.getMaximumTemperature(weather);

        double maximumRainProbability =
                weatherInsightService.getMaximumRainProbability(weather);

        double totalPrecipitation =
                weatherInsightService.getTotalPrecipitation(weather);

        WeatherInsightResponse response = new WeatherInsightResponse();

        response.setWeather(weather);
        response.setMaximumForecastTemperature(maximumTemperature);
        response.setMaximumRainProbability(maximumRainProbability);
        response.setTotalForecastPrecipitation(totalPrecipitation);

        return response;
    }
}