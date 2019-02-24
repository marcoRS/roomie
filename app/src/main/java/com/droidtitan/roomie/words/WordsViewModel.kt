package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WordsViewModel(
  private val uiContext: CoroutineContext = Dispatchers.Main,
  private val repository: WordRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext
    get() = uiContext // By default child coroutines will run on the main thread.

  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    launch(Dispatchers.IO) {
      repository.insert(word)
    }
  }

  fun deleteAllWords() {
    launch(Dispatchers.IO) {
      // Explicitly specify which context to run on, IO in this case.
      repository.deleteAllWords()
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel() // Parent Job cancels all child coroutines.
  }
}