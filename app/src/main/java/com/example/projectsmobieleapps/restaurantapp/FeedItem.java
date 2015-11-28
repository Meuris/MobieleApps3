package com.example.projectsmobieleapps.restaurantapp;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class FeedItem {
    private String name = null;
    private String vicinity = null;
    private String latitude = null;
    private String longitude = null;

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVicinity (String vicinity) {
        this.vicinity = vicinity;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setLatitude (String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude (String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
