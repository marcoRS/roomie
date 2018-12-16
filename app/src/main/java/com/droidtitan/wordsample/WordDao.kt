package com.droidtitan.wordsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
  @Insert
  suspend fun insert(word: Word)

  @Query("DELETE FROM word_table")
  fun deleteAll()

  @Query("SELECT * from word_table ORDER BY word ASC")
  fun getAllWords(): LiveData<List<Word>>
}