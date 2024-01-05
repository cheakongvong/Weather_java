package com.example.weather_java.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.weather_java.Views.HourlyAdapter;
import com.example.weather_java.Domains.Hourly;
import com.example.weather_java.Models.InterfaceAPI;
import com.example.weather_java.Models.WeatherData;
import com.example.weather_java.Models.weather;
import com.example.weather_java.R;
import com.example.weather_java.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = format.format(new Date());

        binding.date.setText(currentDate);
        fetchWeather("Phnom Penh");

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(binding.searchCityBox.getText().toString())){
                    binding.searchCityBox.setError("please enter city");
                    return;
                }

                String CITY_NAME = binding.searchCityBox.getText().toString();
                fetchWeather(CITY_NAME);

            }
        });

        initRecyclerview();
        setVariable();
    }

    void fetchWeather(String location){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);
        Call<WeatherData> call = interfaceAPI.getData(location,"544ed178bc3ad6c15fddca6d19fdadb1","metric");
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    Main mainData = weatherData.getMain();

                    binding.mainTempValue.setText(mainData.getTemp() +"\u2103");
                    String maxMinTemp = String.format("%s/%s\u2103", mainData.getTemp_min(), mainData.getTemp_max());
                    binding.maxandminTemp.setText(maxMinTemp);
                    binding.feelLike.setText(String.valueOf(mainData.getFeels_like())+"\u2103");
                    binding.pressure.setText(String.valueOf(mainData.getPressure())+" hPa");
                    binding.humidity.setText(String.valueOf(mainData.getHumidity())+" %");
                    binding.location.setText(weatherData.getName());

                    List<weather> description = weatherData.getWeather();
                    for(weather data : description){
                        binding.description.setText(data.getDescription());
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });

    }


    private void setVariable() {
        TextView next7dayBtn = findViewById(R.id.nextBtn);
        next7dayBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,FutureActivity.class)));
    }


    private void initRecyclerview() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("7pm",28,"cloudy"));
        items.add(new Hourly("9pm",29,"cloudy"));
        items.add(new Hourly("11pm",30,"wind"));
        items.add(new Hourly("1am",29,"cloudy"));
        items.add(new Hourly("3am",27,"cloudy"));
        items.add(new Hourly("5am",27,"cloudy"));
        items.add(new Hourly("7am",27,"cloudy"));
        items.add(new Hourly("9am",27,"cloudy"));
        items.add(new Hourly("12am",27,"sunny"));

        RecyclerView recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        RecyclerView.Adapter adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }
}