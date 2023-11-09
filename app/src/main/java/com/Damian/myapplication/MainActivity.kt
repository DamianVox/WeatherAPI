package com.Damian.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.Damian.myapplication.Utils.Constants.DAILY_URL
import com.Damian.myapplication.Utils.Constants.Lat
import com.Damian.myapplication.Utils.Constants.Long
import com.Damian.myapplication.Utils.DataProcessor
import com.Damian.myapplication.ViewModel.WeatherVM
import com.Damian.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    //model and bindings
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: WeatherVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.editLat.setText(Lat.toString())
        viewBinding.editLong.setText(Long.toString())

        viewBinding.btnMap.setOnClickListener {
            val intent = Intent(this, StartMapsActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[WeatherVM::class.java]
//-25.909549, 28.147291 --- testing values without user inout
        viewModel.loadWeather(Lat,Long,DAILY_URL)

        viewModel.weatherLive.observe(this, Observer{
            val dataProcessor = DataProcessor()
            // Process the "daily" field
            dataProcessor.processField(it.body()?.daily?.time.toString(), "Time")
            // Process the "max" field
            dataProcessor.processField(it.body()?.daily?.temperature2MMax.toString(), "Max")
            // Process the "min" field
            dataProcessor.processField(it.body()?.daily?.temperature2MMin.toString(), "Min")
        })

    }

}