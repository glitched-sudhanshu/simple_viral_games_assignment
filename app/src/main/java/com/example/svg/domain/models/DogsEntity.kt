package com.example.svg.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogsEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Int,
  val imageUrl: String
)

fun DogsEntity.toDogs() = Dogs(
  imageUrl = imageUrl
)

fun List<DogsEntity>.toDogs() : List<Dogs>{
  return map {
    it.toDogs()
  }
}