// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.Damian.myapplication.model

import com.google.gson.annotations.SerializedName

//Serialization required for field values to gather the information needed from the request
data class Weather (
    val elevation: Long,
    @SerializedName("generationtime_ms")
    val generationtimeMS: Double,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,
    val timezone: String,
    val latitude: Double,
    val daily: Daily,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Long,
    val longitude: Double
)

data class Daily (
    val time: List<String>,
    @SerializedName("snowfall_sum")
    val snowfallSum: List<Any?>,
    @SerializedName("temperature_2m_max")
    val temperature2MMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperature2MMin: List<Double>,
    @SerializedName("rain_sum")
    val rainSum: List<Double>,
    @SerializedName("showers_sum")
    val showersSum: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Long>,
    @SerializedName("precipitation_sum")
    val precipitationSum: List<Double>
)

data class DailyUnits (
    @SerializedName("snowfall_sum")
    val snowfallSum: String,
    @SerializedName("temperature_2m_max")
    val temperature2MMax: String,
    @SerializedName("temperature_2m_min")
    val temperature2MMin: String,
    @SerializedName("rain_sum")
    val rainSum: String,
    val time: String,
    @SerializedName("showers_sum")
    val showersSum: String,
    @SerializedName("weather_code")
    val weatherCode: String,
    @SerializedName("precipitation_sum")
    val precipitationSum: String
)
