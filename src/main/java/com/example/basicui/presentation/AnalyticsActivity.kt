package com.example.basicui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.basicui.R
import com.example.basicui.presentation.theme.BasicUITheme
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.hardware.SensorManager
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorEvent

class AnalyticsActivity : ComponentActivity() {

    //initializing accel variables
    private lateinit var accel_xAxis: TextView
    private lateinit var accel_yAxis: TextView
    private lateinit var accel_zAxis: TextView
    //initialaizing to read sensor values
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) { //main code
        super.onCreate(savedInstanceState)
        setContentView(R.layout.analytics_activity)

        //link the accel values to the UI
        accel_xAxis = findViewById(R.id.xAxisTextView)
        accel_yAxis = findViewById(R.id.yAxisTextView)
        accel_zAxis = findViewById(R.id.zAxisTextView)
        //access the sensorManager service
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //read accel values
        val sensorAccel: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensorEventListener: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                //update and display read values to the Smartwatch UI
                accel_xAxis.setText("X-Axis: " + sensorEvent.values[0])
                accel_yAxis.setText("Y-Axis: " + sensorEvent.values[1])
                accel_zAxis.setText("Z-Axis: " +sensorEvent.values[2])
            }
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }
        //parameters to read the accelerometer only in sensor manager
        sensorManager.registerListener(
            sensorEventListener,
            sensorAccel,
            SensorManager.SENSOR_DELAY_NORMAL
        )


    }//end of main code





}//end of profileActivity