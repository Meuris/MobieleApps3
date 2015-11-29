package com.example.projectsmobieleapps.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by MichielAdmin on 29/11/2015.
 */
public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView nameTextView = (TextView) findViewById(R.id.restaurantName);
        TextView vicinityTextView = (TextView) findViewById(R.id.vicinity);
        TextView latitudeTextView = (TextView) findViewById(R.id.latitude);
        TextView longitudeTextView = (TextView) findViewById(R.id.longitude);

        Intent intent = getIntent();

        String vicinity = intent.getStringExtra("vicinity");
        String name = intent.getStringExtra("name");
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");

        nameTextView.setText(name);
        vicinityTextView.setText(vicinity);
        latitudeTextView.setText(latitude);
        longitudeTextView.setText(longitude);
    }

    @Override
    public void onClick(View v) {

    }
}
