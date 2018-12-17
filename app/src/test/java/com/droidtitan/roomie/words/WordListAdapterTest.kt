package com.droidtitan.roomie.words

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.droidtitan.roomie.data.Word
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WordListAdapterTest {

  private val context = ApplicationProvider.getApplicationContext<Context>()

  @Test fun getItemCount() {
    val adapter = WordListAdapter(context)
    assertEquals(0, adapter.itemCount)

    adapter.setWords(arrayListOf(Word("word"), Word("another")))
    assertEquals(2, adapter.itemCount)
  }
}