package com.droidtitan.roomie.words

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidtitan.roomie.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_words.*
import kotlinx.android.synthetic.main.content_activity_words.*

class WordsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_words)
    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      (fragment as? WordsFragment)?.onFabClick()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode != Activity.RESULT_OK) {
      Snackbar.make(fab, R.string.empty_not_saved, Snackbar.LENGTH_LONG).show()
    }
  }
}