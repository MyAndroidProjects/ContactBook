package com.study.riseof.contactBookAndWeather.weather.presenter;

import android.content.Context;

import com.study.riseof.contactBookAndWeather.weather.model.WeatherForecast;

import java.util.List;

public interface MVPContract {
    interface WeatherView {
        void setWeatherForecastRecyclerAdapter(List<WeatherForecast> weatherForecasts);

        void setTownInfoText(String name, String coordinates);
    }

    interface WeatherPresenter {
        void attachView(WeatherView weatherView);

        void setContext(Context context);

        void viewIsAvailable();

        void deAttachView();

        void onMenuButtonUpdate();

        void onMenuButtonHome();

        void onDestroy();
    }

    interface WeatherDataLoader {
        void startExecute(String weatherSitePath);
    }

}
