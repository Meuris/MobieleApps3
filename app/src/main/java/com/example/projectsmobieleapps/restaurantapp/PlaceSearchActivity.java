package com.example.projectsmobieleapps.restaurantapp;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class PlaceSearchActivity extends ListActivity {
    ArrayList restaurantList;

    private MainActivity main = new MainActivity();
    private String google_Key = "AIzaSyD7Or2O3wMiBNa4FFXpSEMBC4ruxiYHC1A";
    private String latitude = String.valueOf(main.latitude);
    private String longitude = String.valueOf(main.longitude);
    private String radius = main.radius;

    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesearch);
        
    }
}
