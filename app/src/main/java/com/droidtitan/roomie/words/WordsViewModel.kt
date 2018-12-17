package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.*

class WordsViewModel(private val repository: WordRepository) : ViewModel() {

  private val job = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + job)

  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    uiScope.launch {
      repository.insert(word)
    }
  }

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }

  fun deleteAllWords() {
    uiScope.launch {
      withContext(Dispatchers.IO) { repository.deleteAllWords() }
    }
  }
}