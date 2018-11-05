package com.study.riseof.contactBookAndWeather.weather.model;

import android.content.Context;

import com.study.riseof.contactBookAndWeather.R;

public class Town {
    private int DEGREE_SYMBOL_CODE = 186;
    private int townArrayIndex;
    private String latitude;
    private String longitude;

    private enum TownList {
        UNKNOWN("", 0),
        NOVOSIBIRSK("99", 1);

        String index;
        int arrayIndex;

        TownList(String index, int arrayIndex) {
            this.index = index;
            this.arrayIndex = arrayIndex;
        }
    }

    public Town(String townIndex, String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.townArrayIndex = 0;
        for (TownList val : TownList.values()) {
            if (townIndex.equals(val.index)) {
                this.townArrayIndex = val.arrayIndex;
            }
        }
    }

    public String getCoordinates(Context context) {
        return String.format(context.getString(R.string.coordinates),
                latitude, ((char) DEGREE_SYMBOL_CODE),
                longitude, ((char) DEGREE_SYMBOL_CODE));
    }

    public String getTown(Context context) {
        return context.getResources().getStringArray(R.array.town)[townArrayIndex];
    }

}
