package com.study.riseof.contactBookAndWeather.weather.model;

import android.content.Context;

import com.study.riseof.contactBookAndWeather.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class WeatherForecast {
    private GregorianCalendar calendar;
    private int todIndex;
    private int cloudinessIndex;
    private int precipitationIndex;
    private int minPressure;
    private int maxPressure;
    private int minTemperature;
    private int maxTemperature;
    private int minWindSpeed;
    private int maxWindSpeed;
    private int windDirectionIndex;
    private int minRelativeWet;
    private int maxRelativeWet;
    private int minHeat;
    private int maxHeat;

    public void setTod(String tod) {
        todIndex = Integer.valueOf(tod);
    }

    public void setDate(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
    }

    public void setCloudiness(String cloudiness) {
        cloudinessIndex = Integer.valueOf(cloudiness);
    }

    public void setPrecipitation(String precipitation) {
        precipitationIndex = Integer.valueOf(precipitation);
    }

    public void setMinPressure(String minPressure) {
        this.minPressure = Integer.valueOf(minPressure);
    }

    public void setMaxPressure(String maxPressure) {
        this.maxPressure = Integer.valueOf(maxPressure);
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = Integer.valueOf(minTemperature);
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = Integer.valueOf(maxTemperature);
    }

    public void setMinWindSpeed(String minWindSpeed) {
        this.minWindSpeed = Integer.valueOf(minWindSpeed);
    }

    public void setMaxWindSpeed(String maxWindSpeed) {
        this.maxWindSpeed = Integer.valueOf(maxWindSpeed);
    }

    public void setWindDirection(String windDirection) {
        this.windDirectionIndex = Integer.valueOf(windDirection);
    }

    public void setMinRelativeWet(String minRelativeWet) {
        this.minRelativeWet = Integer.valueOf(minRelativeWet);
    }

    public void setMaxRelativeWet(String maxRelativeWet) {
        this.maxRelativeWet = Integer.valueOf(maxRelativeWet);
    }

    public void setMinHeat(String minHeat) {
        this.minHeat = Integer.valueOf(minHeat);
    }

    public void setMaxHeat(String maxHeat) {
        this.maxHeat = Integer.valueOf(maxHeat);
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public int getTodIndex() {
        return todIndex;
    }

    public int getCloudinessIndex() {
        return cloudinessIndex;
    }

    public int getPrecipitationIndex() {
        return precipitationIndex;
    }

    public int getMinPressure() {
        return minPressure;
    }

    public int getMaxPressure() {
        return maxPressure;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinWindSpeed() {
        return minWindSpeed;
    }

    public int getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public int getWindDirectionIndex() {
        return windDirectionIndex;
    }

    public int getMinRelativeWet() {
        return minRelativeWet;
    }

    public int getMaxRelativeWet() {
        return maxRelativeWet;
    }

    public int getMinHeat() {
        return minHeat;
    }

    public int getMaxHeat() {
        return maxHeat;
    }
}
