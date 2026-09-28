package com.agriflow.agriflow.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    private CurrentWeather current;
    private HourlyWeather hourly;

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public HourlyWeather getHourly() {
        return hourly;
    }

    public void setHourly(HourlyWeather hourly) {
        this.hourly = hourly;
    }

    public static class CurrentWeather {

        @JsonProperty("temperature_2m")
        private double temperature_2m;

        @JsonProperty("relative_humidity_2m")
        private double relative_humidity_2m;

        @JsonProperty("precipitation")
        private double precipitation;

        @JsonProperty("wind_speed_10m")
        private double wind_speed_10m;

        public double getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(double temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public double getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public void setRelative_humidity_2m(double relative_humidity_2m) {
            this.relative_humidity_2m = relative_humidity_2m;
        }

        public double getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(double precipitation) {
            this.precipitation = precipitation;
        }

        public double getWind_speed_10m() {
            return wind_speed_10m;
        }

        public void setWind_speed_10m(double wind_speed_10m) {
            this.wind_speed_10m = wind_speed_10m;
        }
    }

    public static class HourlyWeather {

        private String[] time;

        @JsonProperty("temperature_2m")
        private double[] temperature_2m;

        @JsonProperty("relative_humidity_2m")
        private double[] relative_humidity_2m;

        @JsonProperty("precipitation_probability")
        private double[] precipitation_probability;

        @JsonProperty("precipitation")
        private double[] precipitation;

        public String[] getTime() {
            return time;
        }

        public void setTime(String[] time) {
            this.time = time;
        }

        public double[] getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(double[] temperature_2m) {
            this.temperature_2m = temperature_2m;
        }

        public double[] getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public void setRelative_humidity_2m(double[] relative_humidity_2m) {
            this.relative_humidity_2m = relative_humidity_2m;
        }

        public double[] getPrecipitation_probability() {
            return precipitation_probability;
        }

        public void setPrecipitation_probability(double[] precipitation_probability) {
            this.precipitation_probability = precipitation_probability;
        }

        public double[] getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(double[] precipitation) {
            this.precipitation = precipitation;
        }
    }
}