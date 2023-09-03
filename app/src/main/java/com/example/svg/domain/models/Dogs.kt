package com.example.svg.domain.models

data class Dogs(
  val imageUrl : String
)

fun Dogs.toDogsEntity() = DogsEntity(
  imageUrl = imageUrl
)