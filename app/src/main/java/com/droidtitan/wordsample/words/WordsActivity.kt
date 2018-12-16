package com.droidtitan.wordsample.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidtitan.wordsample.R
import kotlinx.android.synthetic.main.activity_main.*

class WordsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
  }
}