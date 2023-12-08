package com.example.weather_java.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.weather_java.Adapters.HourlyAdapter;
import com.example.weather_java.Domains.Hourly;
import com.example.weather_java.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initRecyclerview();
        setVariable();
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