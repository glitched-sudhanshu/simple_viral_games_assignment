package com.example.svg.data.repository

import com.example.svg.data.datasource.database.DogsDao
import com.example.svg.data.datasource.models.toDogs
import com.example.svg.data.datasource.models.toDogsEntity
import com.example.svg.domain.repository.Repository
import com.example.svg.domain.models.Dogs
import com.example.svg.data.datasource.network.RetrofitApiInstance
import com.example.svg.domain.models.toDogs
import com.example.svg.util.ResourceV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

class RepositoryImpl(private val dao: DogsDao) : Repository {

  override suspend fun getRandomDogs(): Flow<ResourceV2<Dogs>> {
    return flow {
      try {
        emit(ResourceV2.Loading())
        val response = RetrofitApiInstance.api.getRandomDogs()
        if (response.isSuccessful) {
          val body = response.body()
          if (body == null) {
            emit(ResourceV2.Error("Dogs not available at the moment!"))
          } else {
            emit(ResourceV2.Success(body.toDogs()))
            // Use Dispatchers.IO for database operations
            withContext(Dispatchers.IO) {
              val noOfDogs = dao.getNoOfDogs().first()
              if (noOfDogs >= 5) {
                try {
                  val lruDog = dao.getLRUDog().first()
                    dao.removeLRUDog(lruDog)
                } catch (exception: Exception) {
                  emit(ResourceV2.Error("Unable to delete LRU dog!"))
                }
              }
              dao.cacheDog(body.toDogsEntity())
            }
          }
        }
      } catch (exception: IOException) {
        emit(ResourceV2.Error("Network error while fetching dog from server!"))
      } catch (exception: Exception) {
        emit(ResourceV2.Error("An error occurred while fetching dog from server"))
      }
    }.flowOn(Dispatchers.IO) // Ensure the Flow runs on an IO thread
  }

  override suspend fun getCachedDogs(): Flow<ResourceV2<List<Dogs>>> {
    return flow<ResourceV2<List<Dogs>>> {
      try {
        emit(ResourceV2.Loading())
        emitAll(dao.getAllDogs().map {
          ResourceV2.Success(it.toDogs())
        })
      } catch (exception: Exception) {
        emit(ResourceV2.Error(exception.message))
      }
    }.flowOn(Dispatchers.IO)
  }
}