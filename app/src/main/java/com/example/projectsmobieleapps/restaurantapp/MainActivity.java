package com.example.projectsmobieleapps.restaurantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Places;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener, View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

    private SeekBar radiusSeekBar;
    private EditText radiusEditText;
    public static int radius = 3000;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static double longitude;
    public static double latitude;

    private Button getRestaurantsButton;
    private Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiusSeekBar = (SeekBar) findViewById(R.id.radiusSeekBar);
        radiusEditText = (EditText) findViewById(R.id.radiusEditText);

        getRestaurantsButton = (Button) findViewById(R.id.getRestaurantsButton);
        locationButton = (Button) findViewById(R.id.showYourLocation);

        radiusSeekBar.setOnSeekBarChangeListener(this);
        radiusEditText.setOnEditorActionListener(this);

        getRestaurantsButton.setOnClickListener(this);
        locationButton.setOnClickListener(this);

        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radiusEditText.setText(String.valueOf(progress));
        radius = progress * 1000;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_UNSPECIFIED)) {
            radius = Integer.valueOf(radiusEditText.getText().toString());
            radiusSeekBar.setProgress(Integer.valueOf(radius));
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == getRestaurantsButton) {
            Intent ItemsActivityIntent = new Intent(MainActivity.this, ItemsActivity.class);
            startActivity(ItemsActivityIntent);
        }
        else if (v == locationButton) {
            Intent mapsIntent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(mapsIntent);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            longitude = mLastLocation.getLongitude();
            latitude = mLastLocation.getLatitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
