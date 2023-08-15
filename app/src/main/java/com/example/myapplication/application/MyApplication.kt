package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.data.datasource.database.DogsDatabase
import com.example.myapplication.data.repository.RepositoryImpl

class MyApplication : Application() {

  private val database by lazy {
    DogsDatabase.getDatabase(this@MyApplication)
  }
  val repository by lazy {
    RepositoryImpl(database.getDogsDao())
  }
}
