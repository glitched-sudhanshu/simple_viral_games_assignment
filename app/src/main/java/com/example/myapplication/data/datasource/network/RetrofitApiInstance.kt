package com.example.myapplication.data.datasource.network

import com.example.myapplication.util.Constants.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiInstance {

  companion object {

    private val retrofit by lazy {
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
      Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(client)
        .build()
    }

    val api: RetrofitApi by lazy {
      retrofit.create(RetrofitApi::class.java)
    }
  }
}