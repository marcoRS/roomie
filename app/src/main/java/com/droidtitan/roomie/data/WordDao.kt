package com.droidtitan.roomie.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
  @Insert
  suspend fun insert(word: Word)

  @Query("DELETE FROM word_table")
  suspend fun deleteAll()

  @Query("SELECT * from word_table ORDER BY word ASC")
  fun getAllWords(): Flow<List<Word>>
}