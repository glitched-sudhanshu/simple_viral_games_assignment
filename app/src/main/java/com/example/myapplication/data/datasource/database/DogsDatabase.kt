package com.example.myapplication.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.domain.models.DogsEntity

@Database(
  entities = [DogsEntity::class],
  version = 1
)
abstract class DogsDatabase : RoomDatabase() {

  abstract fun getDogsDao(): DogsDao

  companion object {

    @Volatile
    private var INSTANCE: DogsDatabase? = null

    fun getDatabase(context: Context): DogsDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          DogsDatabase::class.java,
          "dogs_db"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}

