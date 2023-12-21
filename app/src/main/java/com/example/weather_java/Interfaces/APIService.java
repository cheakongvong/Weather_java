package com.example.weather_java.Interfaces;
import com.example.weather_java.Models.MainCurrentModels;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    // as we are making get request
    // so we are displaying GET as annotation.
    // and inside we are passing
    // last parameter for our url.
    @GET("weather_forecast.json")

    // as we are calling data from array
    // so we are calling it with json object
    // and naming that method as getCourse();
    Call<MainCurrentModels> getMainCurrent();
}
