package com.example.projectsmobieleapps.restaurantapp;

import java.util.ArrayList;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class Feed {
    private String name = null;
    private String longitude = null;
    private ArrayList<FeedItem> items;

    public Feed() {
        items = new ArrayList<FeedItem>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int addItem(FeedItem item) {
        items.add(item);
        return items.size();
    }

    public FeedItem getItem(int index) {
        return items.get(index);
    }

    public ArrayList<FeedItem> getAllItems() {
        return items;
    }
}
