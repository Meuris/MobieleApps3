package com.example.projectsmobieleapps.restaurantapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;



public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener, View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

    private SeekBar radiusSeekBar;
    private EditText radiusEditText;
    private String radius;

    private TextView longitudeTestView;
    private TextView latitudeTestView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    private static final String TAG = "MainActivity";
    private static final String KEY_IN_RESOLUTION = "is_in_resolution";
    private boolean mIsInResolution;
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIsInResolution = savedInstanceState.getBoolean(KEY_IN_RESOLUTION, false);
        }
        setContentView(R.layout.activity_main);

        radiusSeekBar = (SeekBar) findViewById(R.id.radiusSeekBar);
        radiusEditText = (EditText) findViewById(R.id.radiusEditText);

        longitudeTestView = (TextView) findViewById(R.id.longitudeTestView);
        latitudeTestView = (TextView) findViewById(R.id.latitudeTestView);

        radiusSeekBar.setOnSeekBarChangeListener(this);
        radiusEditText.setOnEditorActionListener(this);

        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
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
        Log.d(TAG, "Failed to connect");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radiusEditText.setText(String.valueOf(progress));
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
            radius = radiusEditText.getText().toString();
            radiusSeekBar.setProgress(Integer.valueOf(radius));
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            longitudeTestView.setText(String.valueOf(mLastLocation.getLatitude()));
            latitudeTestView.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
