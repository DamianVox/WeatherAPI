package com.Damian.myapplication.Utils

import android.util.Log
import com.Damian.myapplication.Utils.Constants.dateArray
import com.Damian.myapplication.Utils.Constants.tMaxArray
import com.Damian.myapplication.Utils.Constants.tMinArray
import com.Damian.myapplication.Utils.Constants.tRainArray
import com.Damian.myapplication.Utils.Constants.tWeatherCodeArray
import com.Damian.myapplication.Utils.Constants.tWeatherStatus

class DataProcessor {
    fun processField(data: String, Name: String) {
        //Splitting up the content received into arrays that can be used in the application
        val fieldList = data.replace("[", "").replace("]", "").replace(" ", "").split(",")
        if (Name == "Time")
        {
            dateArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Time", "$Name=$field")
            }
        }
        if (Name == "Max")
        {
            tMaxArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Max", "$Name=$field")
            }
        }
        if (Name == "Min")
        {
            tMinArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Min", "$Name=$field")
            }
        }
        if (Name == "Weathercode")
        {
            tWeatherCodeArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Weathercode", "$Name=$field")
            }
        }
        if (Name == "Rain")
        {
            tRainArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Rain", "$Name=$field")
            }
        }


    }

}