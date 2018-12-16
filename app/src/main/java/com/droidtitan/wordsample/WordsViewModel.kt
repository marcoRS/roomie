package com.droidtitan.wordsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WordsViewModel(private val repository: WordRepository) : ViewModel() {

  private val job = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + job)

  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    // For a non suspend function that needs to run in the background you can also use
    // withContext(Dispatchers.IO){} within launch
    uiScope.launch {
      repository.insert(Word(word))
    }
  }

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}