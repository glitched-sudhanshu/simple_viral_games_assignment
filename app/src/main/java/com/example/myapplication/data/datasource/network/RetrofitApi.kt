package com.example.myapplication.data.datasource.network

import com.example.myapplication.data.datasource.models.ServerDogs
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

  @GET("api/breeds/image/random")
  suspend fun getRandomDogs() : Response<ServerDogs>
}