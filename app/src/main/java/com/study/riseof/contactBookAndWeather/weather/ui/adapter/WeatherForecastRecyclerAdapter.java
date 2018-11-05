package com.study.riseof.contactBookAndWeather.weather.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.riseof.contactBookAndWeather.R;
import com.study.riseof.contactBookAndWeather.weather.model.WeatherForecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastRecyclerAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerAdapter.Holder> {

    private LayoutInflater inflater;
    private List<WeatherForecast> weatherForecastList;
    private Context context;

    public WeatherForecastRecyclerAdapter(Context context, List<WeatherForecast> weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler_view_weather_forecast, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final WeatherForecast weatherForecast = weatherForecastList.get(position);
        holder.forecastWeekdayValue.setText(weatherForecast.getWeekday(context));
        holder.forecastDateValue.setText(weatherForecast.getDate(context));
        holder.forecastTodValue.setText(weatherForecast.getTod(context));
        holder.forecastTimeValue.setText(weatherForecast.getTime(context));
        holder.forecastTemperatureValue.setText(weatherForecast.getTemperature(context));
        holder.forecastHeatValue.setText(weatherForecast.getHeat(context));
        holder.forecastCloudinessValue.setText(weatherForecast.getCloudiness(context));
        holder.forecastPrecipitationValue.setText(weatherForecast.getPrecipitation(context));
        holder.forecastWindValue.setText(weatherForecast.getWind(context));
        holder.forecastPressureValue.setText(weatherForecast.getPressure(context));
        holder.forecastRelativeWetValue.setText(weatherForecast.getRelativeWet(context));

    }

    @Override
    public int getItemCount() {
        return weatherForecastList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.forecast_weekday_value)
        TextView forecastWeekdayValue;
        @BindView(R.id.forecast_date_value)
        TextView forecastDateValue;
        @BindView(R.id.forecast_tod_value)
        TextView forecastTodValue;
        @BindView(R.id.forecast_time_value)
        TextView forecastTimeValue;
        @BindView(R.id.forecast_temperature_value)
        TextView forecastTemperatureValue;
        @BindView(R.id.forecast_heat_value)
        TextView forecastHeatValue;
        @BindView(R.id.forecast_cloudiness_value)
        TextView forecastCloudinessValue;
        @BindView(R.id.forecast_precipitation_value)
        TextView forecastPrecipitationValue;
        @BindView(R.id.forecast_wind_value)
        TextView forecastWindValue;
        @BindView(R.id.forecast_pressure_value)
        TextView forecastPressureValue;
        @BindView(R.id.forecast_relative_wet_value)
        TextView forecastRelativeWetValue;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
