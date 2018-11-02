package com.study.riseof.contactBookAndWeather.weather.model;

import android.util.Log;

import com.study.riseof.contactBookAndWeather.MainActivity;

public class Town {
    private String index;
    private String name;
    private String latitude;
    private String longitude;

    private enum TownListEn {
        NOVOSIBIRSK("99", "Novosibirsk"),
        TOMSK("98", "Tomsk");
        String index;
        String name;

        TownListEn(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    private enum TownListRu {
        NOVOSIBIRSK_RU("99", "Новосибирск"),
        TOMSK_RU("98", "Томск");

        String index;
        String name;

        TownListRu(String index, String name) {
            this.index = index;
            this.name = name;
        }
    }


    public Town(String index, String latitude, String longitude) {
        enumValues(TownListEn.class);
        enumValues(TownListRu.class);
        enumValues2(TownListRu.values());
        this.index = index;
        this.latitude = latitude;
        this.longitude = longitude;
        switch (MainActivity.language) {
            case ENGLISH:
                for (TownListEn val : TownListEn.values()) {
                    if (index.equals(val.index)) {
                        this.name = val.name;
                        break;
                    }
                }
                break;
            case RUSSIAN:
                for (TownListRu val : TownListRu.values()) {
                    if (index.equals(val.index)) {
                        this.name = val.name;
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }


    public <T extends Enum<T>> void enumValues(Class<T> enumType) {
        for (T c : enumType.getEnumConstants()) {
            Log.d("myLog", "c.name() " + c.name());
            Log.d("myLog", "c.toString() " + c.toString());
            System.out.println(c.name());
        }
    }

    public <T extends Enum<T>> void enumValues2(T[] enumType) {
        for (T c : enumType) {
            Log.d("myLog", "c.name()222 " + c.name());
            Log.d("myLog", "c.toString()222 " + c.toString());
            System.out.println(c.name());
        }
    }
}
