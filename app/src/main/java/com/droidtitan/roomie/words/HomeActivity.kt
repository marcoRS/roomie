package com.droidtitan.roomie.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidtitan.roomie.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    setSupportActionBar(toolbar)
  }
}