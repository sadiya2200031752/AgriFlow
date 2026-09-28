package com.agriflow.agriflow.weather;

import org.springframework.stereotype.Service;

@Service
public class WeatherInsightService {

    public double getMaximumTemperature(WeatherResponse weatherResponse) {

        double maximumTemperature = Double.MIN_VALUE;

        for (double temperature :
                weatherResponse.getHourly().getTemperature_2m()) {

            if (temperature > maximumTemperature) {
                maximumTemperature = temperature;
            }
        }

        return maximumTemperature;
    }

    public double getMaximumRainProbability(WeatherResponse weatherResponse) {

        double maximumProbability = 0;

        for (double probability :
                weatherResponse.getHourly().getPrecipitation_probability()) {

            if (probability > maximumProbability) {
                maximumProbability = probability;
            }
        }

        return maximumProbability;
    }

    public double getTotalPrecipitation(WeatherResponse weatherResponse) {

        double totalPrecipitation = 0;

        for (double precipitation :
                weatherResponse.getHourly().getPrecipitation()) {

            totalPrecipitation += precipitation;
        }

        return totalPrecipitation;
    }
}