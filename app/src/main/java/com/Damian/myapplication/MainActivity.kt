package com.Damian.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Damian.myapplication.ViewModel.WeatherVM
import com.Damian.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: WeatherVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[WeatherVM::class.java]
        //-25.909549, 28.147291
        viewModel.loadWeather(-25.909549,28.147291,"weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,showers_sum,snowfall_sum")

        viewModel.weatherLive.observe(this, Observer{
            Log.d("Output Body",it.body().toString())
            Log.d("Output",it.toString())
            //viewBinding.test.text = it?.temperature2MMax.toString()
        })

    }
}