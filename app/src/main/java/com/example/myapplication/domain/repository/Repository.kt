package com.example.myapplication.domain.repository

import com.example.myapplication.data.datasource.database.DogsDatabase
import com.example.myapplication.domain.models.Dogs
import com.example.myapplication.domain.models.DogsEntity
import com.example.myapplication.util.ResourceV2
import kotlinx.coroutines.flow.Flow

interface Repository {

  suspend fun getRandomDogs(): Flow<ResourceV2<Dogs>>

  suspend fun getCachedDogs(): Flow<ResourceV2<List<Dogs>>>

  suspend fun cacheDog(dogsEntity: DogsEntity)

  suspend fun removeDog(dogsEntity: DogsEntity)
}