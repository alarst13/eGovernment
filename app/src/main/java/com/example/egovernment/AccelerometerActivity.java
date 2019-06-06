package com.example.egovernment;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity {

   TextView textViewX , textViewY , textView;

   Sensor sensor;
   SensorManager sensorManager;
   SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        textView = findViewById(R.id.ACCELEROMETER_text);
        textViewX = findViewById(R.id.ACCELEROMETER_x);
        textViewY = findViewById(R.id.ACCELEROMETER_y);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textViewX.setText(String.valueOf(mkDegree(event.values[0] , event.values[2])));
                textViewY.setText(String.valueOf(mkDegree(event.values[1] , event.values[2])));
                if (event.values[0] * event.values[0] < 0.05 && event.values[1] * event.values[1] < 0.05){
                    textView.setBackgroundColor(Color.rgb(0 , 240 , 0));
                }else {
                    textView.setBackgroundColor(Color.rgb(240 , 0 , 0));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(sensorEventListener , sensor , SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static double mkDegree(float a , float b){
        return Math.atan((double) a/b) * (180/Math.PI);
    }
}
