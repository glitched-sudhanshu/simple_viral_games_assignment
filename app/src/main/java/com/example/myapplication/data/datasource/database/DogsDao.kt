package com.example.myapplication.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.models.DogsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun cacheDog(dogsEntity: DogsEntity): Long

  @Query("SELECT * FROM dogs")
  fun getAllDogs(): Flow<List<DogsEntity>>

  @Delete
  suspend fun removeDog(dogsEntity: DogsEntity)
}