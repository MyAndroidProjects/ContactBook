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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastRecyclerAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerAdapter.Holder> {
    private final int PRECIPITATION_OFFSET = -3;
    private final int CLOUDINESS_OFFSET = 1;
    private final String DATE_FORMAT = "yyyy.MM.dd";
    private final String WEEKDAY_FORMAT = "EEEE";
    private final String TIME_FORMAT = "HH:mm";

    private LayoutInflater inflater;
    private List<WeatherForecast> weatherForecastList;
    private Context context;
    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatWeekday;
    private SimpleDateFormat formatTime;

    public WeatherForecastRecyclerAdapter(Context context, List<WeatherForecast> weatherForecastList) {
        this.weatherForecastList = weatherForecastList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        formatDate = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        formatWeekday = new SimpleDateFormat(WEEKDAY_FORMAT, Locale.getDefault());
        formatTime = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
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

        holder.forecastWeekdayValue.setText(formatWeekday.format(weatherForecast.getCalendar().getTime()));
        holder.forecastDateValue.setText(formatDate.format(weatherForecast.getCalendar().getTime()));
        holder.forecastTodValue.setText(context.getResources().getStringArray(R.array.tod)[weatherForecast.getTodIndex()]);
        holder.forecastTimeValue.setText(formatTime.format(weatherForecast.getCalendar().getTime()));
        holder.forecastTemperatureValue.setText(
                String.format(context.getString(R.string.temperature), weatherForecast.getMinTemperature(), weatherForecast.getMaxTemperature()));
        holder.forecastHeatValue.setText(String.format(context.getString(R.string.temperature), weatherForecast.getMinHeat(), weatherForecast.getMaxHeat()));
        holder.forecastCloudinessValue.setText(context.getResources().getStringArray(R.array.cloudiness)[weatherForecast.getCloudinessIndex() + CLOUDINESS_OFFSET]);
        holder.forecastPrecipitationValue.setText(context.getResources().getStringArray(R.array.precipitation)[weatherForecast.getPrecipitationIndex() + PRECIPITATION_OFFSET]);
        holder.forecastWindValue.setText(
                String.format(context.getString(R.string.wind), weatherForecast.getMinWindSpeed(), weatherForecast.getMaxWindSpeed(),
                        context.getResources().getStringArray(R.array.windDirection)[weatherForecast.getWindDirectionIndex()])
        );
        holder.forecastPressureValue.setText(String.format(context.getString(R.string.pressure), weatherForecast.getMinPressure(), weatherForecast.getMaxPressure()));
        holder.forecastRelativeWetValue.setText(String.format(context.getString(R.string.relativeWet), weatherForecast.getMinRelativeWet(), weatherForecast.getMaxRelativeWet()));
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
