package com.study.riseof.contactBookAndWeather.weather.model;


import com.study.riseof.contactBookAndWeather.R;

public class Town {
    private int townId;
    private final String latitude;
    private final String longitude;

    private enum SelectedTown {
        UNKNOWN("", R.string.town_unknown),
        NOVOSIBIRSK("99", R.string.town_novosibirsk);

        final String index;
        final int townId;

        SelectedTown(String index, int townId) {
            this.index = index;
            this.townId = townId;
        }
    }

    public Town(String townIndex, String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.townId = 0;
        for (SelectedTown val : SelectedTown.values()) {
            if (townIndex.equals(val.index)) {
                this.townId = val.townId;
            }
        }
    }

    public int getTownId() {
        return townId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
