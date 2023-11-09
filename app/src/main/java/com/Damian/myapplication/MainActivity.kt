package com.Damian.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.Damian.myapplication.Utils.Constants.DAILY_URL
import com.Damian.myapplication.Utils.Constants.Lat
import com.Damian.myapplication.Utils.Constants.Long
import com.Damian.myapplication.Utils.Constants.dateArray
import com.Damian.myapplication.Utils.Constants.tMaxArray
import com.Damian.myapplication.Utils.Constants.tMinArray
import com.Damian.myapplication.Utils.Constants.tRainArray
import com.Damian.myapplication.Utils.Constants.tWeatherCodeArray
import com.Damian.myapplication.Utils.DataProcessor
import com.Damian.myapplication.ViewModel.WeatherVM
import com.Damian.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {


    //model and bindings
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: WeatherVM
    private var unit: Boolean = false
    private var unitVal: String = "C"

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



        viewBinding.btnWeather.setOnClickListener {

            if (!viewBinding.checkUnit.isChecked){
                unit = false
                unitVal = "C"
            }
            else{
                unit = true
                unitVal = "F"
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
                // Process the "Weathcode" field
                dataProcessor.processField(it.body()?.daily?.weatherCode.toString(), "Weathercode")
                // Process the "Rain" field
                dataProcessor.processField(it.body()?.daily?.rainSum.toString(), "Rain")
                runBlocking {
                    // Use the async function to start the asynchronous operation
                    val deferred = async {
                        performResults()
                    }
                    deferred.await()
                }

            })

        }

    }

     private suspend fun performResults() {
        Toast.makeText(this,"Please Wait", Toast.LENGTH_SHORT).show()
        delay(2000)
        Log.d("output results", "start ${dateArray[0].toString()},${tMaxArray[0].toString()},${tMinArray[0].toString()},${tWeatherCodeArray[0].toString()},${tRainArray[0].toString()}")

         if (unitVal =="C") {
             //Today
             viewBinding.txtTodayDate.text = "Today: " + dateArray[0]
             viewBinding.txtTodayMax.text = "Max Temp: " + tMaxArray[0] + unitVal
             viewBinding.txtTodayMin.text = "Min Temp: " + tMinArray[0] + unitVal
             viewBinding.txtTodayWeatherCode.text = "Weather Code: " + tWeatherCodeArray[0]
             viewBinding.txtTodayRain.text = "Rain : " + tRainArray[0]
         } else {
             viewBinding.txtTodayDate.text = "Today: " + dateArray[0]
             viewBinding.txtTodayMax.text = "Max Temp: " + ((tMaxArray[0].toDouble()*9/5)+32).toString() + unitVal
             viewBinding.txtTodayMin.text = "Min Temp: " + ((tMinArray[0].toDouble()*9/5)+32).toString() + unitVal
             viewBinding.txtTodayWeatherCode.text = "Weather Code: " + tWeatherCodeArray[0]
             viewBinding.txtTodayRain.text = "Rain : " + tRainArray[0]

         }
    }

}