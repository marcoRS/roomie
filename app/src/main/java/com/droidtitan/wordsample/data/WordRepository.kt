package com.droidtitan.wordsample.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {
  val allWords: LiveData<List<Word>> = wordDao.getAllWords()

  @WorkerThread
  suspend fun insert(word: String) {
    wordDao.insert(Word(word))
  }

  @WorkerThread
  fun deleteAllWords() {
    wordDao.deleteAll()
  }
}