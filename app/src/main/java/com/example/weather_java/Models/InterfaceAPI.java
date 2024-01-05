package com.example.weather_java.Models;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceAPI {
    @GET("data/2.5/weather")
    Call<WeatherData> getData(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units
            );
}
