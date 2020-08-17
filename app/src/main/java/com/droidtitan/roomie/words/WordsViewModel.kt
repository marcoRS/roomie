package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WordsViewModel(private val repository: WordRepository) : ViewModel() {
  val allWords: LiveData<List<Word>>
    get() = repository.allWords

  fun insert(word: String) {
    viewModelScope.launch {
      repository.insert(word)
    }
  }

  fun deleteAllWords() {
    viewModelScope.launch {
      repository.deleteAllWords()
    }
  }
}