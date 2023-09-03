package com.example.svg.domain.repository

import com.example.svg.domain.models.Dogs
import com.example.svg.util.ResourceV2
import kotlinx.coroutines.flow.Flow

interface Repository {

  suspend fun getRandomDogs(): Flow<ResourceV2<Dogs>>

  suspend fun getCachedDogs(): Flow<ResourceV2<List<Dogs>>>

  suspend fun cacheDog(dogs: Dogs)

  suspend fun removeLRUDog(dogsId: Int)

  suspend fun getLRUDog() : Flow<ResourceV2<Int>>
}