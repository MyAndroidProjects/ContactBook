package com.study.riseof.contactBookAndWeather.weather.ui.activity;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.weather.model.WeatherForecast;
import com.study.riseof.contactBookAndWeather.weather.presenter.MVPContract;
import com.study.riseof.contactBookAndWeather.weather.presenter.WeatherForecastPresenter;
import com.study.riseof.contactBookAndWeather.weather.ui.adapter.WeatherForecastRecyclerAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends AppCompatActivity implements MVPContract.WeatherView {

    private MVPContract.WeatherPresenter presenter;

    @BindView(R.id.weather_forecast_toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_weather_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.town_name)
    TextView townNameText;
    @BindView(R.id.town_coordinates)
    TextView townCoordinatesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);
        setActionBar();
        setPresenter();
    }

    private void setPresenter() {
        presenter = WeatherForecastPresenter.getInstance();
        presenter.attachView(this);
        presenter.setContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsAvailable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_update_forecast:
                presenter.onMenuButtonUpdate();
                return true;
            case android.R.id.home:
                presenter.onMenuButtonHome();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } else {
            Log.d("myLog", "getSupportActionBar() == null");
            throw new ClassCastException("getSupportActionBar() == null");
        }
    }

    @Override
    public void setWeatherForecastRecyclerAdapter(List<WeatherForecast> weatherForecasts) {
        WeatherForecastRecyclerAdapter adapter = new WeatherForecastRecyclerAdapter(this, weatherForecasts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTownInfoText(String name, String coordinates) {
        townNameText.setText(name);
        townCoordinatesText.setText(coordinates);
    }
}
