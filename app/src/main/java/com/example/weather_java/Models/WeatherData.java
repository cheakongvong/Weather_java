package com.example.weather_java.Models;

import com.example.weather_java.Activity.Main;

import java.util.List;

public class WeatherData {


    private List<weather> weather;
    private Main main;
    private String name;
    public WeatherData(List<com.example.weather_java.Models.weather> weather, Main main, String name) {
        this.weather = weather;
        this.main = main;
        this.name = name;
    }

    public List<com.example.weather_java.Models.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.weather_java.Models.weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
