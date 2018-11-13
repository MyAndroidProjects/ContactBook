package com.study.riseof.contactBookAndWeather.weather.model;


import com.study.riseof.contactBookAndWeather.R;

public enum Town {

    UNKNOWN("", R.string.town_unknown, 0, 0),
    NOVOSIBIRSK("99", R.string.town_novosibirsk, 55, 83);

    final String index;
    final int townNameId;
    final int latitude;
    final int longitude;

    Town(String index, int townNameId, int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.index = index;
        this.townNameId = townNameId;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getTownNameId() {
        return townNameId;
    }

    public String getIndex() {
        return index;
    }
}


