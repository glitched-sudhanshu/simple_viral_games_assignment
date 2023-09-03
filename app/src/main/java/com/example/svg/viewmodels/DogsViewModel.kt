package com.example.svg.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svg.domain.models.Dogs
import com.example.svg.domain.repository.Repository
import com.example.svg.util.ResourceV2
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DogsViewModel(private val repository: Repository) : ViewModel() {

  private val _latestRandomDog : MutableLiveData<ResourceV2<Dogs>> = MutableLiveData()
  val latestRandomDog : LiveData<ResourceV2<Dogs>> = _latestRandomDog

  private val _cachedDogs : MutableLiveData<ResourceV2<List<Dogs>>> = MutableLiveData()
  val cachedDogs : LiveData<ResourceV2<List<Dogs>>> = _cachedDogs



  private fun getCachedDogs() {
    viewModelScope.launch {
      val response = repository.getCachedDogs()
      response.collect{
        when(it) {
          is ResourceV2.Loading -> _cachedDogs.postValue(ResourceV2.Loading())

          is ResourceV2.Success -> _cachedDogs.postValue(ResourceV2.Success(it.data))

          is ResourceV2.Error -> _cachedDogs.postValue(ResourceV2.Error(it.message))
        }
      }
    }
  }

  private fun getDogs() {
    viewModelScope.launch {
      val response = repository.getRandomDogs()
      response.collect{
        when(it) {
          is ResourceV2.Loading -> _latestRandomDog.postValue(ResourceV2.Loading())

          is ResourceV2.Success -> {
            _latestRandomDog.postValue(ResourceV2.Success(it.data))
            //1 -> sizeofDB
            if(1 > 20)
            {
              repository.getLRUDog().collect{ itr->
                when(itr)
                {
                  is ResourceV2.Loading -> _latestRandomDog.postValue(ResourceV2.Loading())

                  is ResourceV2.Success -> {
                    repository.removeLRUDog(itr.data)
                  }

                  is ResourceV2.Error -> {
                    _latestRandomDog.postValue(ResourceV2.Error(itr.message))
                  }
                }
              }

            }
            repository.cacheDog(it.data)
          }

          is ResourceV2.Error -> _latestRandomDog.postValue(ResourceV2.Error(it.message))
        }
      }
    }
  }

}