package com.droidtitan.wordsample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
  @Insert
  suspend fun insert(word: Word)

  // Suspend not supported on delete queries as of latest Room version: 2.1.0-alpha03
  @Query("DELETE FROM word_table")
  fun deleteAll()

  @Query("SELECT * from word_table ORDER BY word ASC")
  fun getAllWords(): LiveData<List<Word>>
}