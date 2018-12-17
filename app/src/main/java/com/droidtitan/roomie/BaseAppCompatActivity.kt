package com.droidtitan.roomie

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

/**
 * Base app compat activity used to set up Calligraphy.
 */
@SuppressLint("Registered")
abstract class BaseAppCompatActivity : AppCompatActivity() {

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
  }
}