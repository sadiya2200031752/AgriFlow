package com.agriflow.agriflow.weather;

public class WeatherInsightResponse {

    private WeatherResponse weather;
    private double maximumForecastTemperature;
    private double maximumRainProbability;
    private double totalForecastPrecipitation;

    public WeatherResponse getWeather() {
        return weather;
    }

    public void setWeather(WeatherResponse weather) {
        this.weather = weather;
    }

    public double getMaximumForecastTemperature() {
        return maximumForecastTemperature;
    }

    public void setMaximumForecastTemperature(double maximumForecastTemperature) {
        this.maximumForecastTemperature = maximumForecastTemperature;
    }

    public double getMaximumRainProbability() {
        return maximumRainProbability;
    }

    public void setMaximumRainProbability(double maximumRainProbability) {
        this.maximumRainProbability = maximumRainProbability;
    }

    public double getTotalForecastPrecipitation() {
        return totalForecastPrecipitation;
    }

    public void setTotalForecastPrecipitation(double totalForecastPrecipitation) {
        this.totalForecastPrecipitation = totalForecastPrecipitation;
    }
}