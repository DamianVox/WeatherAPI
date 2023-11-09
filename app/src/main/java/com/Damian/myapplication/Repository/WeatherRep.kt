package com.Damian.myapplication.Repository

import com.Damian.myapplication.model.Weather
import com.Damian.myapplication.Utils.RetroObject
import com.Damian.myapplication.model.Daily
import retrofit2.Response

class WeatherRep {
    private val apiService = RetroObject.apiService

    suspend fun getWeather(latitude: Double,
                           longitude: Double,
                           daily: String): Response<Weather> {
        return apiService.getWeather(latitude,
            longitude,
            daily)
    }
}