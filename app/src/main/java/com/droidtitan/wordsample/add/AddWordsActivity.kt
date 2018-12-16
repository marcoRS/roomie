package com.droidtitan.wordsample.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidtitan.wordsample.R
import kotlinx.android.synthetic.main.activity_add_words.*

class AddWordsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_words)
    setSupportActionBar(toolbar)

    supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(true)
      title = getString(R.string.add_words)
    }
  }
}