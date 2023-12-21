package com.example.weather_java.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.weather_java.Adapters.FutureAdapter;
import com.example.weather_java.Domains.FutureDomain;
import com.example.weather_java.R;

import java.util.ArrayList;

public class FutureActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTomorrow;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);

        initRecyclerView();
        setVariable();
    }

    private void setVariable() {
        View backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> startActivity(new Intent(FutureActivity.this, MainActivity.class)));

    }


    private void initRecyclerView() {
        ArrayList<FutureDomain> items = new ArrayList<>();

        items.add(new FutureDomain("Monday",    "storm","Storm",32,24));
        items.add(new FutureDomain("Tuesday",   "cloudy","Cloudy",31,25));
        items.add(new FutureDomain("Wednesday", "windy","Windy",32,23));
        items.add(new FutureDomain("Thursday",  "cloudy_sunny","Cloudy Sunny",32,25));
        items.add(new FutureDomain("Friday",    "sunny","Sunny",30,26));
        items.add(new FutureDomain("Saturday",  "rainy","Rainy",29,22));
        items.add(new FutureDomain("Sunday",    "rainy","Rainy",28,23));

        recyclerView = findViewById(R.id.view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterTomorrow = new FutureAdapter(items);
        recyclerView.setAdapter(adapterTomorrow);
    }
}