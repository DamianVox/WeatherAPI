package com.Damian.myapplication.Utils

object Constants {
    //URL Variables
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    const val DAILY_URL = "weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,showers_sum,snowfall_sum"

    // User Location
    val Lat = null
    val Long = null

    // API Return Variables
    var dateArray:Array<String> = arrayOf()
    var tMaxArray:Array<String> = arrayOf()
    var tMinArray:Array<String> = arrayOf()
    var rainArray:Array<String> = arrayOf()



}