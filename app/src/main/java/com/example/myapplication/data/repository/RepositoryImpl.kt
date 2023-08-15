package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.database.DogsDao
import com.example.myapplication.data.datasource.database.DogsDatabase
import com.example.myapplication.data.datasource.models.toDogs
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.domain.models.Dogs
import com.example.myapplication.data.datasource.network.RetrofitApiInstance
import com.example.myapplication.domain.models.DogsEntity
import com.example.myapplication.domain.models.toDogs
import com.example.myapplication.util.ResourceV2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

  override suspend fun cacheDog(dogsEntity: DogsEntity) {
    dao.cacheDog(dogsEntity = dogsEntity)
  }

  override suspend fun removeDog(dogsEntity: DogsEntity) {
    dao.removeDog(dogsEntity = dogsEntity)
  }
}