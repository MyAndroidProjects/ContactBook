package com.study.riseof.contactBookAndWeather.weather.model;


public class Town {
    private int townArrayIndex;
    private final String latitude;
    private final String longitude;

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

    public int getTownArrayIndex() {
        return townArrayIndex;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
