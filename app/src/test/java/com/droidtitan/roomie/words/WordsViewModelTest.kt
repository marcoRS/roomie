package com.droidtitan.roomie.words

import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class WordsViewModelTest {

  @Mock private lateinit var repo: WordRepository
  private lateinit var model: WordsViewModel

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    model = WordsViewModel(Dispatchers.Unconfined, repo)
  }

  @Test
  fun insert() = runBlocking {
    val testWord = "word"
    model.insert(testWord)
    verify(repo).insert(testWord)
  }

  @Test
  fun deleteAllWords() {
    model.deleteAllWords()
    verify(repo).deleteAllWords()
  }
}