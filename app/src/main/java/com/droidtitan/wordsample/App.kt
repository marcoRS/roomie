package com.droidtitan.wordsample

import android.app.Application
import com.droidtitan.wordsample.words.wordsModule
import org.koin.android.ext.android.startKoin

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(wordsModule))
  }
}