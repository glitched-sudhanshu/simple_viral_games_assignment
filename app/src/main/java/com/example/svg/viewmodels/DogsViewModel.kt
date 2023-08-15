package com.example.svg.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svg.domain.repository.Repository
import com.example.svg.util.ResourceV2
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DogsViewModel(private val repository: Repository) : ViewModel() {


  private fun getDogs() {
    viewModelScope.launch {
      val x = repository.getRandomDogs()
      x.collectLatest {
        when(it)
        {
          is ResourceV2.Loading ->
            Log.d("dogs api", "getDogs: loading")

          is ResourceV2.Success -> Log.d("dogs api s","getDogs: succ" )

          is ResourceV2.Error -> Log.d("dogs api s","getDogs: error" )
        }
      }
    }
  }

}