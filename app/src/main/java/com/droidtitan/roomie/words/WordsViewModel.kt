package com.droidtitan.roomie.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.droidtitan.roomie.data.Word
import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WordsViewModel(private val repository: WordRepository) : ViewModel() {
  val allWords: LiveData<List<Word>>
    get() = repository.allWords.flowOn(Dispatchers.Main)
      .asLiveData(context = viewModelScope.coroutineContext)

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