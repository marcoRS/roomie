package com.droidtitan.wordsample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository internal constructor(private val wordDao: WordDao) {
  val allWords: LiveData<List<Word>> = wordDao.getAllWords()

  @WorkerThread
  suspend fun insert(word: Word) {
    wordDao.insert(word)
  }
}