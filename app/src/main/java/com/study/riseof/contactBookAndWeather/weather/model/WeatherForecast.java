package com.study.riseof.contactBookAndWeather.weather.model;

import com.study.riseof.contactBookAndWeather.MainActivity;
import com.study.riseof.contactBookAndWeather.MainActivity.Language;
import com.study.riseof.contactBookAndWeather.R;

import static com.study.riseof.contactBookAndWeather.MainActivity.Language.ENGLISH;
import static com.study.riseof.contactBookAndWeather.MainActivity.Language.RUSSIAN;

public class WeatherForecast {

    private enum Tod {
        // tod - время суток, для которого составлен прогноз: 0 - ночь 1 - утро, 2 - день, 3 - вечер
        NIGHT("0", "Night"),
        MORNING("1", "Morning"),
        DAY("2", "Day"),
        EVENING("3", "Evening");

        String index;
        String dayPart;

        Tod(String index, String dayPart) {
            this.index = index;
            this.dayPart = dayPart;
        }
    }

    private enum TodRu {
        // tod - время суток, для которого составлен прогноз: 0 - ночь 1 - утро, 2 - день, 3 - вечер
        NIGHT("0", "Ночь"),
        MORNING("1", "Утро"),
        DAY("2", "День"),
        EVENING("3", "Вечер");

        String index;
        String dayPart;

        TodRu(String index, String dayPart) {
            this.index = index;
            this.dayPart = dayPart;
        }
    }

    private enum Weekday {
        //weekday -	день недели, 1 - воскресенье, 2 - понедельник, и т.д.
        SUNDAY("1", "Sunday"),
        MONDAY("2", "Monday"),
        TUESDAY("3", "Tuesday"),
        WEDNESDAY("4", "Wednesday"),
        THURSDAY("5", "Thursday"),
        FRIDAY("6", "Friday"),
        SATURDAY("7", "Saturday");

        String index;
        String name;

        Weekday(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum WeekdayRu {
        //weekday -	день недели, 1 - воскресенье, 2 - понедельник, и т.д.
        SUNDAY("1", "Воскресенье"),
        MONDAY("2", "Понедельник"),
        TUESDAY("3", "Вторник"),
        WEDNESDAY("4", "Среда"),
        THURSDAY("5", "Четверг"),
        FRIDAY("6", "Пятница"),
        SATURDAY("7", "Суббота");

        String index;
        String name;

        WeekdayRu(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum Cloudiness {
        //cloudiness -	облачность по градациям: -1 - туман, 0 - ясно, 1 - малооблачно, 2 - облачно, 3 - пасмурно
        FOG("-1", "fog"),
        CLEAR("0", "clear"),
        LOW_CLOUD("1", "low cloud"),
        CLOUDY("2", "cloudy"),
        OVERCAST("3", "overcast");

        String index;
        String name;

        Cloudiness(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum CloudinessRu {
        //cloudiness -	облачность по градациям: -1 - туман, 0 - ясно, 1 - малооблачно, 2 - облачно, 3 - пасмурно
        FOG("-1", "туман"),
        CLEAR("0", "ясно"),
        LOW_CLOUD("1", "малооблачно"),
        CLOUDY("2", "облачно"),
        OVERCAST("3", "пасмурно");

        String index;
        String name;

        CloudinessRu(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum Precipitation {
        // precipitation -	тип осадков: 3 - смешанные, 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
        MIXED("3", "mixed"),
        RAIN("4", "rain"),
        CLOUDBURST("5", "cloudburst"),
        SNOW("6", "snow"),
        WET_SNOW("7", "wet snow"),
        THUNDERSTORM("8", "thunderstorm"),
        NO_DATA("9", "no data"),
        NO_PRECIPITATION("10", "no precipitation");

        String index;
        String name;

        Precipitation(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum PrecipitationRu {
        // precipitation -	тип осадков: 3 - смешанные, 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
        MIXED("3", "смешанные"),
        RAIN("4", "дождь"),
        CLOUDBURST("5", "ливень"),
        SNOW("6", "снег"),
        WET_SNOW("7", "мокрый снег"),
        THUNDERSTORM("8", "гроза"),
        NO_DATA("9", "нет данных"),
        NO_PRECIPITATION("10", "без осадков");

        String index;
        String name;

        PrecipitationRu(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum WindDirection {
        // direction - направление ветра , 0 - северный, 1 - северо-восточный, и т.д
        N("0", "N"),
        NE("1", "NE"),
        E("2", "E"),
        SE("3", "SE"),
        S("4", "S"),
        SW("5", "SW"),
        W("6", "W"),
        NW("7", "NW");

        String index;
        String name;

        WindDirection(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum WindDirectionRu {
        // direction - направление ветра , 0 - северный, 1 - северо-восточный, и т.д
        N("0", "С"),
        NE("1", "СВ"),
        E("2", "В"),
        SE("3", "ЮВ"),
        S("4", "Ю"),
        SW("5", "ЮЗ"),
        W("6", "З"),
        NW("7", "СЗ");

        String index;
        String name;

        WindDirectionRu(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private int DEGREE_SYMBOL_CODE = 186;
    private final String EMPTY_STRING = "";
    private final String CELSIUS = "C";
    private final String MM_HG = "mmHg";
    private final String MM_HG_RU = "мм.рт.ст.";
    private final String MS = "m/s";
    private final String MS_RU = "м/с";

    private MainActivity.Language language = ENGLISH;
    private String day;
    private String month;
    private String year;
    private String date;
    private String hour;
    private String tod;
    private String weekday;
    private String predict;
    private String cloudiness;
    private String precipitation;
    //   private String rPower;
    //   private String sPower;
    private String minPressure;
    private String maxPressure;
    private String minTemperature;
    private String maxTemperature;
    private String minWindSpeed;
    private String maxWindSpeed;
    private String windDirection;
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

    public void setDate(String day, String month, String year) {
        this.date = day + "." + month + "." + year;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTod(String tod) {
        switch (MainActivity.language) {
            case ENGLISH:
                for (Tod val : Tod.values()) {
                    if (tod.equals(val.index)) {
                        this.tod = val.dayPart;
                        return;
                    }
                }
                break;
            case RUSSIAN:
                for (TodRu val : TodRu.values()) {
                    if (tod.equals(val.index)) {
                        this.tod = val.dayPart;
                        return;
                    }
                }
                break;
            default:
                break;
        }
        this.tod = tod;
    }

    public void setWeekday(String weekday) {
        switch (MainActivity.language) {
            case ENGLISH:
                for (Weekday val : Weekday.values()) {
                    if (weekday.equals(val.index)) {
                        this.weekday = val.name;
                        return;
                    }
                }
                break;
            case RUSSIAN:
                for (WeekdayRu val : WeekdayRu.values()) {
                    if (weekday.equals(val.index)) {
                        this.weekday = val.name;
                        return;
                    }
                }
                break;
            default:
                break;
        }
        this.weekday = weekday;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public void setCloudiness(String cloudiness) {
        switch (MainActivity.language) {
            case ENGLISH:
                for (Cloudiness val : Cloudiness.values()) {
                    if (cloudiness.equals(val.index)) {
                        this.cloudiness = val.name;
                        return;
                    }
                }
                break;
            case RUSSIAN:
                for (CloudinessRu val : CloudinessRu.values()) {
                    if (cloudiness.equals(val.index)) {
                        this.cloudiness = val.name;
                        return;
                    }
                }
                break;
            default:
                break;
        }
        this.cloudiness = cloudiness;
    }

    public void setPrecipitation(String precipitation) {
        switch (MainActivity.language) {
            case ENGLISH:
                for (Precipitation val : Precipitation.values()) {
                    if (precipitation.equals(val.index)) {
                        this.precipitation = val.name;
                        return;
                    }
                }
                break;
            case RUSSIAN:
                for (PrecipitationRu val : PrecipitationRu.values()) {
                    if (precipitation.equals(val.index)) {
                        this.precipitation = val.name;
                        return;
                    }
                }
                break;
            default:
                break;
        }
        this.precipitation = precipitation;
    }

    /*
        public void setRPower(String rPower) {
            this.rPower = rPower;
        }

        public void setSPower(String sPower) {
            this.sPower = sPower;
        }
    */
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
        switch (MainActivity.language) {
            case ENGLISH:
                for (WindDirection val : WindDirection.values()) {
                    if (windDirection.equals(val.index)) {
                        this.windDirection = val.name;
                        return;
                    }
                }
                break;
            case RUSSIAN:
                for (WindDirectionRu val : WindDirectionRu.values()) {
                    if (windDirection.equals(val.index)) {
                        this.windDirection = val.name;
                        return;
                    }
                }
                break;
            default:
                break;
        }
        this.windDirection = windDirection;
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

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getTime() {
        return hour + ":00";
    }

    public String getTod() {
        return tod;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getPredict() {
        return predict;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getPrecipitation() {
        return precipitation;
    }
/*
    public String getRPower() {
        return rPower;
    }

    public String getSPower() {
        return sPower;
    }*/

    public String getMaxPressure() {
        return maxPressure;
    }

    public String getMinPressure() {
        return minPressure;
    }

    public String getPressure() {
        switch (MainActivity.language) {
            case ENGLISH:
                return minPressure + ".." + maxPressure + " " + MM_HG;
            case RUSSIAN:
                return minPressure + ".." + maxPressure + " " + MM_HG_RU;
            default:
                return EMPTY_STRING;
        }
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getTemperature() {
        return minTemperature + ".." + maxTemperature + "," + ((char) DEGREE_SYMBOL_CODE) + CELSIUS;
    }

    public String getMinWindSpeed() {
        return minWindSpeed;
    }

    public String getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWind() {
        switch (MainActivity.language) {
            case ENGLISH:
                return minWindSpeed + "-" + maxWindSpeed + MS + " " + windDirection;
            case RUSSIAN:
                return minWindSpeed + "-" + maxWindSpeed + MS_RU + " " + windDirection;
            default:
                return EMPTY_STRING;
        }
    }

    public String getMinRelativeWet() {
        return minRelativeWet;
    }

    public String getMaxRelativeWet() {
        return maxRelativeWet;
    }

    public String getRelativeWet() {
        return minRelativeWet + "-" + maxRelativeWet + " %";
    }

    public String getMaxHeat() {
        return maxHeat;
    }

    public String getMinHeat() {
        return minHeat;
    }

    public String getHeat() {
        return maxHeat + ".." + maxHeat + "," + ((char) DEGREE_SYMBOL_CODE) + CELSIUS;
    }
}
