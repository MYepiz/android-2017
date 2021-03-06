package com.example.marty.piedrapapelytijeras;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class PPT extends AppCompatActivity {
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            onPause();
            if (mAccel > 30) {
                Random r = new Random();
                int jugada = r.nextInt(4 - 1) + 1;
                switch (jugada) {
                    case 1: ((ImageView)findViewById(R.id.fist)).setVisibility(View.VISIBLE);
                            ((ImageView)findViewById(R.id.paper)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.scissor)).setVisibility(View.GONE);
                        break;
                    case 2: ((ImageView)findViewById(R.id.fist)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.paper)).setVisibility(View.VISIBLE);
                            ((ImageView)findViewById(R.id.scissor)).setVisibility(View.GONE);
                        break;
                    case 3: ((ImageView)findViewById(R.id.fist)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.paper)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.scissor)).setVisibility(View.VISIBLE);
                        break;
                    default:((ImageView)findViewById(R.id.fist)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.paper)).setVisibility(View.GONE);
                            ((ImageView)findViewById(R.id.scissor)).setVisibility(View.GONE);
                        break;
                }
                TextView counter = (TextView)findViewById(R.id.shakeCounter);
                int shakes = Integer.parseInt(counter.getText().toString());
                shakes++;
                counter.setText(""+shakes);
            }
            onResume();
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt);
        ((RelativeLayout)findViewById(R.id.layoutR)).setBackgroundColor(Color.parseColor("#eeff41"));

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }
}

