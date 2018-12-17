package com.droidtitan.roomie.add

import android.os.Bundle
import com.droidtitan.roomie.BaseAppCompatActivity
import com.droidtitan.roomie.R
import kotlinx.android.synthetic.main.activity_add_words.*

class AddWordsActivity : BaseAppCompatActivity() {

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