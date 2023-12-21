package com.example.weather_java.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentConditionModels {

    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("temp_c")
    @Expose
    private String tempC;
    @SerializedName("high_low")
    @Expose
    private String highLow;
    private String rain;
    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;
    private String humidity;
    @SerializedName("condition")
    @Expose
    private WeatherConditionModels condition;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getTempC() {
        return tempC;
    }

    public String getHighLow() {
        return highLow;
    }

    public String getRain() {
        return rain;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public WeatherConditionModels getCondition() {
        return condition;
    }
}
