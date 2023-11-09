package com.Damian.myapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Damian.myapplication.model.Weather
import com.Damian.myapplication.Repository.WeatherRep
import com.Damian.myapplication.model.Daily
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherVM:ViewModel() {

    private val repo = WeatherRep()
    private val _weather = MutableLiveData<Response<Weather>>()

    val weatherLive: LiveData<Response<Weather>>
        get() = _weather

    fun loadWeather(latitude: Double,
                    longitude: Double,
                    daily: String){
        viewModelScope.launch {
            val response: Response<Weather> = repo.getWeather(latitude,longitude,daily)
            _weather.value = response
        }
     /*   viewModelScope.launch {
            val result = repo.getWeather()
            _weather.postValue(result)
            Log.d("Weather", result.toString())
        }*/
    }

}