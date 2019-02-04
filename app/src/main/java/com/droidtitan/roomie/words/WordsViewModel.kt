package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WordsViewModel(private val repository: WordRepository) : ViewModel(), CoroutineScope {
  private val job = Job() // Used to explicitly cancel all coroutines in this scope.

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    launch(Dispatchers.IO) {
      repository.insert(word)
    }
  }

  fun deleteAllWords() {
    launch(Dispatchers.IO) {
      repository.deleteAllWords()
    }
  }

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}