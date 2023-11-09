package com.Damian.myapplication.Utils

import com.Damian.myapplication.Utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroObject {


    // Create an instance of HttpLoggingInterceptor
    private val loggingInterceptor = HttpLoggingInterceptor()

    // Create an OkHttpClient with the logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    //Create Retrofit instance
    val apiService:ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)

}