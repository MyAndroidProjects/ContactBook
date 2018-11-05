package com.study.riseof.contactBookAndWeather.weather.model;

import android.content.Context;

import com.study.riseof.contactBookAndWeather.MainActivity;
import com.study.riseof.contactBookAndWeather.R;


public class WeatherForecast {

    private enum Tod {
        // tod - время суток, для которого составлен прогноз: 0 - ночь 1 - утро, 2 - день, 3 - вечер
        NIGHT("0", 0),
        MORNING("1", 1),
        DAY("2", 2),
        EVENING("3", 3);

        String index;
        int arrayIndex;

        Tod(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    private enum Weekday {
        //weekday -	день недели, 1 - воскресенье, 2 - понедельник, и т.д.
        SUNDAY("1", 0),
        MONDAY("2", 1),
        TUESDAY("3", 2),
        WEDNESDAY("4", 3),
        THURSDAY("5", 4),
        FRIDAY("6", 5),
        SATURDAY("7", 6);

        String index;
        int arrayIndex;

        Weekday(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    private enum Cloudiness {
        //cloudiness -	облачность по градациям: -1 - туман, 0 - ясно, 1 - малооблачно, 2 - облачно, 3 - пасмурно
        FOG("-1", 0),
        CLEAR("0", 1),
        LOW_CLOUD("1", 2),
        CLOUDY("2", 3),
        OVERCAST("3", 4);

        String index;
        int arrayIndex;

        Cloudiness(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    private enum Precipitation {
        // precipitation -	тип осадков: 3 - смешанные, 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
        MIXED("3", 0),
        RAIN("4", 1),
        CLOUDBURST("5", 2),
        SNOW("6", 3),
        WET_SNOW("7", 4),
        THUNDERSTORM("8", 5),
        NO_DATA("9", 6),
        NO_PRECIPITATION("10", 7);

        String index;
        int arrayIndex;

        Precipitation(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    private enum WindDirection {
        // direction - направление ветра , 0 - северный, 1 - северо-восточный, и т.д
        N("0", 0),
        NE("1", 1),
        E("2", 2),
        SE("3", 3),
        S("4", 4),
        SW("5", 5),
        W("6", 6),
        NW("7", 7);

        String index;
        int arrayIndex;

        WindDirection(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    private int DEGREE_SYMBOL_CODE = 186;

    private String day;
    private String month;
    private String year;
    private String date;
    private String hour;
    private int todIndex;
    private int weekdayIndex;
    private String predict;
    private int cloudinessIndex;
    private int precipitationIndex;
    private String minPressure;
    private String maxPressure;
    private String minTemperature;
    private String maxTemperature;
    private String minWindSpeed;
    private String maxWindSpeed;
    private int windDirectionIndex;
    private String minRelativeWet;
    private String maxRelativeWet;
    private String minHeat;
    private String maxHeat;

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTod(String tod) {
        for (Tod val : Tod.values()) {
            if (tod.equals(val.index)) {
                todIndex = val.arrayIndex;
                return;
            }
        }
    }

    public void setWeekday(String weekday) {
        for (Weekday val : Weekday.values()) {
            if (weekday.equals(val.index)) {
                this.weekdayIndex = val.arrayIndex;
                return;
            }
        }
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public void setCloudiness(String cloudiness) {
        for (Cloudiness val : Cloudiness.values()) {
            if (cloudiness.equals(val.index)) {
                this.cloudinessIndex = val.arrayIndex;
                return;
            }
        }
    }

    public void setPrecipitation(String precipitation) {
        for (Precipitation val : Precipitation.values()) {
            if (precipitation.equals(val.index)) {
                this.precipitationIndex = val.arrayIndex;
                return;
            }
        }
    }

    public void setMinPressure(String minPressure) {
        this.minPressure = minPressure;
    }

    public void setMaxPressure(String maxPressure) {
        this.maxPressure = maxPressure;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinWindSpeed(String minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public void setMaxWindSpeed(String maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public void setWindDirection(String windDirection) {
        for (WindDirection val : WindDirection.values()) {
            if (windDirection.equals(val.index)) {
                this.windDirectionIndex = val.arrayIndex;
                return;
            }
        }
    }

    public void setMinRelativeWet(String minRelativeWet) {
        this.minRelativeWet = minRelativeWet;
    }

    public void setMaxRelativeWet(String maxRelativeWet) {
        this.maxRelativeWet = maxRelativeWet;
    }

    public void setMinHeat(String minHeat) {
        this.minHeat = minHeat;
    }

    public void setMaxHeat(String maxHeat) {
        this.maxHeat = maxHeat;
    }

    public String getDate(Context context) {
        return String.format(context.getString(R.string.date), day, month, year);
    }

    public String getTime(Context context) {
        return String.format(context.getString(R.string.time), hour);
    }

    public String getTod(Context context) {
        return context.getResources().getStringArray(R.array.tod)[todIndex];
    }

    public String getWeekday(Context context) {
        return context.getResources().getStringArray(R.array.weekday)[weekdayIndex];
    }

    public String getCloudiness(Context context) {
        return context.getResources().getStringArray(R.array.cloudiness)[cloudinessIndex];
    }

    public String getPrecipitation(Context context) {
        return context.getResources().getStringArray(R.array.precipitation)[precipitationIndex];
    }

    public String getPressure(Context context) {
        return String.format(context.getString(R.string.pressure), minPressure, maxPressure);
    }

    public String getTemperature(Context context) {
        return String.format(context.getString(R.string.temperature), minTemperature, maxTemperature, ((char) DEGREE_SYMBOL_CODE));
    }

    public String getWind(Context context) {
        return String.format(context.getString(R.string.wind),
                minTemperature, maxTemperature,
                context.getResources().getStringArray(R.array.windDirection)[windDirectionIndex]);
    }


    public String getRelativeWet(Context context) {
        return String.format(context.getString(R.string.relativeWet), minRelativeWet, maxRelativeWet);
    }

    public String getHeat(Context context) {
        return String.format(context.getString(R.string.temperature), minHeat, maxHeat, ((char) DEGREE_SYMBOL_CODE));
    }
}
