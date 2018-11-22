package com.study.riseof.contactBookAndWeather.weather.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.NavUtils;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.weather.model.Town;
import com.study.riseof.contactBookAndWeather.weather.model.WeatherForecast;
import com.study.riseof.contactBookAndWeather.weather.network.WeatherForecastDataLoader;
import com.study.riseof.contactBookAndWeather.weather.network.WeatherForecastParser;

import java.util.List;

public class WeatherForecastPresenter implements WeatherForecastContract.WeatherPresenter, WeatherForecastDataLoader.StopExecuteListener {

    private static WeatherForecastPresenter instance;

    private final String EMPTY_STRING = "";
    private String weatherSitePath;
    private Town town = Town.NOVOSIBIRSK;
    private WeatherForecastDataLoader weatherForecastDataLoader;

    private Context context;
    private WeatherForecastContract.WeatherView weatherView;

    private WeatherForecastPresenter() {
    }

    public static WeatherForecastPresenter getInstance() {
        if (instance == null) {
            instance = new WeatherForecastPresenter();
        }
        return instance;
    }

    private void setTownInfo() {
        String name = context.getString(town.getTownNameId());
        String coordinates = String.format(context.getString(R.string.coordinates), town.getLatitude(), town.getLongitude());
        weatherView.setTownInfoText(name, coordinates);
    }

    private void forecastDataLoading() {
        weatherForecastDataLoader = new WeatherForecastDataLoader();
        WeatherForecastContract.WeatherDataLoader dataLoader = weatherForecastDataLoader;
        weatherForecastDataLoader.setStopExecuteListener(this);
        dataLoader.startExecute(weatherSitePath);
    }

    @Override
    public void attachView(WeatherForecastContract.WeatherView weatherView) {
        this.weatherView = weatherView;
    }

    @Override
    public void deAttachView() {
        weatherView = null;
        context = null;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onMenuButtonUpdate() {
        weatherView.setTownInfoText(context.getString(R.string.loading), EMPTY_STRING);
        forecastDataLoading();
    }

    @Override
    public void viewIsAvailable() {
        weatherSitePath = context.getString(R.string.link_meteoservice_ru);
        weatherView.setTownInfoText(context.getString(R.string.loading), EMPTY_STRING);
        forecastDataLoading();
    }

    @Override
    public void onMenuButtonHome() {
        NavUtils.navigateUpFromSameTask((Activity) context);
    }

    @Override
    public void onActivityDestroy() {
        deAttachView();
        weatherForecastDataLoader.cancel(true);
        weatherForecastDataLoader = null;
    }

    @Override
    public void stopExecute(String xmlData) {
        WeatherForecastParser weatherForecastParser = new WeatherForecastParser();
        if (xmlData != null && weatherForecastParser.parse(xmlData)) {
            List<WeatherForecast> weatherForecasts = weatherForecastParser.getWeatherForecasts();
            weatherView.setWeatherForecastRecyclerAdapter(weatherForecasts);
            setTownInfo();
        }
    }
}
