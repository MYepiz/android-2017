package com.example.marty.marcadora15;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

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
                ((TextView)findViewById(R.id.P1)).setText("0");
                ((TextView)findViewById(R.id.P2)).setText("0");
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
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    public void addPlayer1(View view){
        TextView Score1 = (TextView)findViewById(R.id.P1);
        int S = Integer.parseInt(Score1.getText().toString());
        S++;
        if (S == 15) {
            ganador("1");
            ((TextView)findViewById(R.id.P2)).setText("0");
            S=0;
        }
        Score1.setText(S+"");
    }
    public void addPlayer2(View view){
        TextView Score1 = (TextView)findViewById(R.id.P2);
        int S = Integer.parseInt(Score1.getText().toString());
        S++;
        if (S == 15) {
            ganador("2");
            ((TextView)findViewById(R.id.P1)).setText("0");
            S=0;
        }
        Score1.setText(S+"");
    }
    public void subsPlayer1(View view){
        TextView Score1 = (TextView)findViewById(R.id.P1);
        int S = Integer.parseInt(Score1.getText().toString());
        if (S > 0) {
            S--;
        }
        Score1.setText(S+"");
    }
    public void subsPlayer2(View view){
        TextView Score1 = (TextView)findViewById(R.id.P2);
        int S = Integer.parseInt(Score1.getText().toString());
        if (S > 0) {
            S--;
        }
        Score1.setText(S+"");
    }

    public void ganador(String winner) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("!JUGADOR "+winner+" GANA!");
        // alert.setMessage("Message");

        alert.setPositiveButton("Oooots", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });
        alert.show();
    }
}
