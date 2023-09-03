package com.example.svg.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.svg.domain.models.DogsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun cacheDog(dogsEntity: DogsEntity): Long

  @Query("SELECT * FROM dogs ORDER BY id DESC")
  fun getAllDogs(): Flow<List<DogsEntity>>

  @Query("DELETE FROM dogs WHERE id = :itemId")
  suspend fun removeLRUDog(itemId: Int)

  @Query("SELECT id FROM dogs ORDER BY id ASC LIMIT 1")
  fun getLRUDog(): Flow<Int>

  @Query("SELECT COUNT(id) FROM dogs")
  fun getNoOfDogs(): Flow<Int>
}