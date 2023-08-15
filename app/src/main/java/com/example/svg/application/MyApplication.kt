package com.example.svg.application

import android.app.Application
import com.example.svg.data.datasource.database.DogsDatabase
import com.example.svg.data.repository.RepositoryImpl

class MyApplication : Application() {

  private val database by lazy {
    DogsDatabase.getDatabase(this@MyApplication)
  }
  val repository by lazy {
    RepositoryImpl(database.getDogsDao())
  }
}
