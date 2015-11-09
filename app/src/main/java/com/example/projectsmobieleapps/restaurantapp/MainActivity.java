package com.example.projectsmobieleapps.restaurantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener,
        View.OnKeyListener{

    private TextView radiusAmountTextView;
    private SeekBar radiusSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiusAmountTextView = (TextView) findViewById(R.id.radiusAmountTextView);
        radiusSeekBar = (SeekBar) findViewById(R.id.radiusSeekBar);

        radiusSeekBar.setOnSeekBarChangeListener(this);
        radiusSeekBar.setOnKeyListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showRadius() {
        int progress = radiusSeekBar.getProgress();
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
        radiusAmountTextView.setText(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        showRadius();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
