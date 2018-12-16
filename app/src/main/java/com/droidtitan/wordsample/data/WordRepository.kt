package com.droidtitan.wordsample.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.droidtitan.wordsample.data.Word
import com.droidtitan.wordsample.data.WordDao

class WordRepository(private val wordDao: WordDao) {
  val allWords: LiveData<List<Word>> = wordDao.getAllWords()

  @WorkerThread
  suspend fun insert(word: String) {
    wordDao.insert(Word(word))
  }
}