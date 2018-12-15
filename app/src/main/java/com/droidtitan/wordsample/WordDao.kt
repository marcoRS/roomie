package com.droidtitan.wordsample

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface WordDao {
  @Insert
  fun insert(word: Word)

  @Query("DELETE FROM word_table")
  fun deleteAll()

  @Query("SELECT * from word_table ORDER BY word ASC")
  fun getAllWords(): LiveData<List<Word>>
}