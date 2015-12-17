package com.example.projectsmobieleapps.restaurantapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.SupportMapFragment;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class FileIO extends ListActivity {
    private String latitude = String.valueOf(MainActivity.latitude);
    private String longitude = String.valueOf(MainActivity.longitude);
    private String radius = String.valueOf(MainActivity.radius);
    private final String googleKey = "AIzaSyBDy6vuCYHAOCUBWS1LnLsAPQtsjOcQH14";
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
                out.close();
                in.close();
            } catch (IOException e) {
                Log.e("Restaurant app", e.toString());
            }
        }
    }

    public Feed readFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            FeedHandler theHandler = new FeedHandler();
            xmlreader.setContentHandler(theHandler);

            FileInputStream in = context.openFileInput(FILENAME);

            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            Feed feed = theHandler.getFeed();
            return feed;
        } catch(Exception e) {
            Log.e("Restaurant app", e.toString());
            return null;
        }
    }
}
