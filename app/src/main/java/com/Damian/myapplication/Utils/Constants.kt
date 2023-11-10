package com.Damian.myapplication.Utils

object Constants {
    //URL Variables
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    const val DAILY_URL = "weather_code,temperature_2m_max,temperature_2m_min,precipitation_sum,rain_sum,showers_sum,snowfall_sum"

    // User Location
    var Lat:Double = 0.0
    var Long:Double = 0.0

    // API Return Variables
    var dateArray:Array<String> = arrayOf()
    var tMaxArray:Array<String> = arrayOf()
    var tMinArray:Array<String> = arrayOf()
    var tWeatherCodeArray:Array<String> = arrayOf()
    var tWeatherStatus:Array<String?> = arrayOf()
    var tRainArray:Array<String> = arrayOf()

    //Maps
    var MAPS_API_KEY="AIzaSyAgH6oo14wxkg5VrWGwwneetnIhmzPBm_U"



}