package com.example.weather_java.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherConditionModels {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
