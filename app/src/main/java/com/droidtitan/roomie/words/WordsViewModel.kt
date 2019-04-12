package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WordsViewModel(private val repository: WordRepository) : ViewModel(), CoroutineScope {
  private val job: Job = SupervisorJob()

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job // By default child coroutines will run on the main thread.

  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    launch {
      repository.insert(word)
    }
  }

  fun deleteAllWords() {
    launch {
      repository.deleteAllWords()
    }
  }

  override fun onCleared() {
    super.onCleared()
    job.cancel() // Parent Job cancels all child coroutines.
  }
}