package com.example.projectsmobieleapps.restaurantapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by MichielAdmin on 19/12/2015.
 */
public class SettingsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
