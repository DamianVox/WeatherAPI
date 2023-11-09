package com.Damian.myapplication.Utils

import android.util.Log
import com.Damian.myapplication.Utils.Constants.dateArray
import com.Damian.myapplication.Utils.Constants.tMaxArray
import com.Damian.myapplication.Utils.Constants.tMinArray

class DataProcessor {
    fun processField(data: String, Name: String) {
        val fieldList = data.replace("[", "").replace("]", "").replace(" ", "").split(",")
        if (Name == "Time")
        {
            dateArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("                    val intent = Intent(this, StartMapsActivity::class.java)\n" +
                        "                    startActivity(intent)\n" +
                        "                    finish() Day Time", "$Name=$field")
            }
        }
        if (Name == "Max")
        {
            tMaxArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Time", "$Name=$field")
            }
        }
        if (Name == "Min")
        {
            tMinArray = fieldList.toTypedArray()
            for (field in fieldList) {
                Log.d("Output Day Time", "$Name=$field")
            }
        }


    }

}