package com.example.svg.data.datasource.models

import com.example.svg.domain.models.Dogs
import com.google.gson.annotations.SerializedName

data class ServerDogs(
  @SerializedName("message")
  val imageUrl: String,
  @SerializedName("status")
  val status: String
)

fun ServerDogs.toDogs() = Dogs(
  imageUrl = imageUrl
)