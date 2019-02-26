package com.droidtitan.roomie.words

import com.droidtitan.roomie.data.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
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
    Dispatchers.setMain(Dispatchers.Unconfined)
    model = WordsViewModel(repo)
  }

  @Test
  fun insert() = runBlocking {
    val testWord = "word"
    model.insert(testWord)
    verify(repo).insert(testWord)
  }

  @Test
  fun deleteAllWords() = runBlocking {
    model.deleteAllWords()
    verify(repo).deleteAllWords()
  }
}