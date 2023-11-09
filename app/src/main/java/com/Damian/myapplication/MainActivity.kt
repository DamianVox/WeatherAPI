package com.Damian.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Damian.myapplication.Utils.Constants.DAILY_URL
import com.Damian.myapplication.Utils.Constants.dateArray
import com.Damian.myapplication.Utils.DataProcessor
import com.Damian.myapplication.ViewModel.WeatherVM
import com.Damian.myapplication.databinding.ActivityMainBinding
import com.Damian.myapplication.model.Daily
import com.Damian.myapplication.model.Weather

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: WeatherVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[WeatherVM::class.java]
//-25.909549, 28.147291 --- testing values without user inout
        viewModel.loadWeather(-25.909549,28.147291,DAILY_URL)

        viewModel.weatherLive.observe(this, Observer{
            Log.d("Output Body",it.body().toString())
            Log.d("Output Daily Time", it.body()?.daily?.time.toString())

            val dataProcessor = DataProcessor()
// Process the "daily" field
            dataProcessor.processField(it.body()?.daily?.time.toString(), "Time")
// Process the "max" field
            dataProcessor.processField(it.body()?.daily?.temperature2MMax.toString(), "Max")
// Process the "min" field
            dataProcessor.processField(it.body()?.daily?.temperature2MMin.toString(), "Min")

/* Previous set before setting up array values
            val dateStr = it.body()?.daily?.time.toString()
// Remove the brackets and spaces from the string
            val dateList = dateStr.replace("[", "").replace("]", "").replace(" ", "").split(",")
// Convert the list of date strings into an array
            dateArray = dateList.toTypedArray()
            for (date in dateArray) {
                Log.d("Output Day Time", date)
            }
              Log.d("Output",it.toString())

            //viewBinding.test.text = it?.temperature2MMax.toString()
*/
        })

    }
}