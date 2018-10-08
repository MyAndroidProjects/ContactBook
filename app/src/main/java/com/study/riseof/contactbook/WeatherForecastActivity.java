package com.study.riseof.contactbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastParser.SetLocation{

    private String townIndex;
    private String latitude;
    private String longitude;
 //   WeatherForecastParser weatherForecastParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ButterKnife.bind(this);
        //weatherForecastParser = new WeatherForecastParser(this);
    }


    @Override
    public void location(String townIndex, String latitude, String longitude) {
        this.townIndex = townIndex;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
