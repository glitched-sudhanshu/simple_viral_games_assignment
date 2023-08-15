package com.example.svg.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.svg.domain.repository.Repository

class DogsViewModelProviderFactory(val repository: Repository) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return DogsViewModel(repository) as T
  }
}