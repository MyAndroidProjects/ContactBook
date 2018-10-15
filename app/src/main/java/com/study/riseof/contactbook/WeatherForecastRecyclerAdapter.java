package com.study.riseof.contactbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastRecyclerAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerAdapter.Holder> {

    private LayoutInflater inflater;
    private List<WeatherForecast> weatherForecastList;

    WeatherForecastRecyclerAdapter(Context context, List<WeatherForecast> weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_weather_forecast_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final WeatherForecast weatherForecast = weatherForecastList.get(position);
        holder.forecastWeekdayValue.setText(weatherForecast.getWeekday());
        holder.forecastDateValue.setText(weatherForecast.getDate());
        holder.forecastTodValue.setText(weatherForecast.getTod());
        holder.forecastTimeValue.setText(weatherForecast.getTime());
        holder.forecastTemperatureValue.setText(weatherForecast.getTemperature());
        holder.forecastHeatValue.setText(weatherForecast.getHeat());
        holder.forecastCloudinessValue.setText(weatherForecast.getCloudiness());
        holder.forecastPrecipitationValue.setText(weatherForecast.getPrecipitation());
        holder.forecastWindValue.setText(weatherForecast.getWind());
        holder.forecastPressureValue.setText(weatherForecast.getPressure());
        holder.forecastRelativeWetValue.setText(weatherForecast.getRelativeWet());

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

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
