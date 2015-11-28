package com.example.projectsmobieleapps.restaurantapp;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class FileIO extends ListActivity {
    private static String latitude = String.valueOf(MainActivity.latitude);
    private static String longitude = String.valueOf(MainActivity.longitude);
    private static String radius = String.valueOf(MainActivity.radius);
    private final String googleKey = "AIzaSyD7Or2O3wMiBNa4FFXpSEMBC4ruxiYHC1A";
    private final String URL_STRING = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location="
            + latitude + "," + longitude +"&radius=" + radius + "&types=restaurant&key=" + googleKey;
    private Context context = null;
    private final String FILENAME = "infolist.xml";

    public FileIO (Context context) {
        this.context = context;
    }

    public void downloadFile() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            try {
                URL url = new URL(URL_STRING);

                InputStream in = url.openStream();

                FileOutputStream out = context.openFileOutput(FILENAME, context.MODE_PRIVATE);

                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                while (bytesRead != -1) {
                    out.write(buffer, 0, bytesRead);
                    bytesRead = in.read(buffer);
                }
            } catch (IOException e) {
                Log.e("Restaurant app", e.toString());
            }
        }
    }

    public Feed readFile() {
        
    }
}
