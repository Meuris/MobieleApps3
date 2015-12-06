package com.example.projectsmobieleapps.restaurantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by MichielAdmin on 29/11/2015.
 */
public class ItemsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Feed feed;
    private FileIO io;

//    private TextView titleTextView;
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        io = new FileIO(getApplicationContext());

//        titleTextView = (TextView) findViewById(R.id.titleTextView);
        itemsListView = (ListView) findViewById(R.id.itemsListView);

        itemsListView.setOnItemClickListener(this);

        new DownloadFeed().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("Restaurant app", "Feed downloaded");
            new ReadFeed().execute();
        }
    }

    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            feed = io.readFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("Restaurant app", "Feed read");
            ItemsActivity.this.updateDisplay();
        }
    }

    public void updateDisplay() {
        if (feed == null) {
//            titleTextView.setText("Unables to get feed");
            return;
        }

//        titleTextView.setText(feed.getName());

        ArrayList<FeedItem> items = feed.getAllItems();

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for (FeedItem item : items) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("vicinity", item.getVicinity());
            map.put("name", item.getName());
            data.add(map);
        }

        int resource = R.layout.listview;
        String[] from = {"vicinity", "name"};
        int[] to = {R.id.vicinity, R.id.restaurantName};

        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);

        Log.d("Restaurant app", "Feed displayed");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FeedItem item = feed.getItem(position);

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + item.getLatitude() + "," + item.getLongitude() + "&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

        //Intent intent = new Intent(this, MapsActivity.class);

        /*intent.putExtra("name", item.getName());
        intent.putExtra("vicinity", item.getVicinity());*/
        /*intent.putExtra("latitude", item.getLatitude());
        intent.putExtra("longitude", item.getLongitude());

        this.startActivity(intent);*/

        /*Intent mapsIntent = new Intent(ItemsActivity.this, MapsActivity.class);
        startActivity(mapsIntent);*/
    }
}
