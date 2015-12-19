package com.example.projectsmobieleapps.restaurantapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by MichielAdmin on 19/12/2015.
 */
public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
