package com.example.svg.data.repository

import com.example.svg.data.datasource.database.DogsDao
import com.example.svg.data.datasource.models.toDogs
import com.example.svg.domain.repository.Repository
import com.example.svg.domain.models.Dogs
import com.example.svg.data.datasource.network.RetrofitApiInstance
import com.example.svg.domain.models.DogsEntity
import com.example.svg.domain.models.toDogs
import com.example.svg.domain.models.toDogsEntity
import com.example.svg.util.ResourceV2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

class RepositoryImpl(val dao: DogsDao) : Repository {

  override suspend fun getRandomDogs(): Flow<ResourceV2<Dogs>> {
    return flow{
      try {
        emit(ResourceV2.Loading())
        val response = RetrofitApiInstance.api.getRandomDogs()
        if(response.isSuccessful)
        {
          val body = response.body()
          if(body == null) emit(ResourceV2.Error("Dogs not available at the moment"))
          else emit(ResourceV2.Success(body.toDogs()))
        }
      } catch (exception : Exception)
      {
        emit(ResourceV2.Error(exception.message))
      }
    }
  }

  override suspend fun getCachedDogs(): Flow<ResourceV2<List<Dogs>>> {
    return flow{
      try{
        emit(ResourceV2.Loading())
        dao.getAllDogs().transform{
          emit(it.toDogs())
        }
      } catch (exception : Exception){
        emit(ResourceV2.Error(exception.message))
      }
    }
  }

  override suspend fun cacheDog(dogs: Dogs) {
    val dogsEntity = dogs.toDogsEntity()
    dao.cacheDog(dogsEntity = dogsEntity)
  }

  override suspend fun removeLRUDog(dogsId: Int) {
    dao.removeLRUDog(dogsId)
  }

  override suspend fun getLRUDog(): Flow<ResourceV2<Int>> {
    return flow{
      try{
        emit(ResourceV2.Loading())
        dao.getLRUDog().transform{
          emit(it)
        }
      } catch (exception : Exception){
        emit(ResourceV2.Error(exception.message))
      }
    }
  }
}