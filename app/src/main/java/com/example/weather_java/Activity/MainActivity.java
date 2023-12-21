package com.example.weather_java.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather_java.Adapters.HourlyAdapter;
import com.example.weather_java.Domains.Hourly;
import com.example.weather_java.Interfaces.APIService;
import com.example.weather_java.Models.MainCurrentModels;
import com.example.weather_java.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView description;
    private ImageView imageView;
    private TextView dateTime;
    private TextView temperature;
    private TextView highLow;
    private TextView rain;
    private TextView windSpeed;
    private TextView humidity;
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initRecyclerview();
        setVariable();
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.imageView);
        dateTime = findViewById(R.id.dateTime);
        temperature = findViewById(R.id.temperature);
        highLow = findViewById(R.id.highLow);
        rain = findViewById(R.id.rain);
        windSpeed = findViewById(R.id.kilometer_per_hour);
        humidity = findViewById(R.id.humidity_percent);
        getCurrentCondition();
    }

    private void getCurrentCondition() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kimly19.github.io/Weather_Forecast_Api/")
                // on below line we are calling add Converter
                // factory as GSON converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        APIService apiService = retrofit.create(APIService.class);
        Call<MainCurrentModels> call = apiService.getMainCurrent();
        call.enqueue(new Callback<MainCurrentModels>() {
            @Override
            public void onResponse(Call<MainCurrentModels> call, Response<MainCurrentModels> response) {
                if (response.isSuccessful()) {
                    MainCurrentModels modal = response.body();
                    description.setText(modal.getCurrent().getCondition().getDescription());
                    Picasso.get().load(modal.getCurrent().getCondition().getIcon()).into(imageView);
                    dateTime.setText(modal.getCurrent().getLastUpdated());
                    temperature.setText(modal.getCurrent().getTempC());
                    highLow.setText(modal.getCurrent().getHighLow());
                    rain.setText(modal.getCurrent().getRain());
                    windSpeed.setText(modal.getCurrent().getWindSpeed());
                    humidity.setText(modal.getCurrent().getHumidity());
                }
            }

            @Override
            public void onFailure(Call<MainCurrentModels> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVariable() {
        TextView next7dayBtn = findViewById(R.id.nextBtn);
        next7dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FutureActivity.class));
            }
        });
    }

    private void initRecyclerview() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("9pm",28,"cloudy"));
        items.add(new Hourly("11pm",29,"sunny"));
        items.add(new Hourly("12pm",30,"wind"));
        items.add(new Hourly("1am",29,"rainy"));
        items.add(new Hourly("2am",27,"storm"));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }
}