package com.example.weather_java.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCurrentModels {
    @SerializedName("current")
    @Expose
    private CurrentConditionModels current;

    public CurrentConditionModels getCurrent() {
        return current;
    }
}
