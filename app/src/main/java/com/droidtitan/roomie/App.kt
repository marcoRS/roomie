package com.droidtitan.roomie

import android.app.Application
import com.droidtitan.roomie.words.wordsModule
import org.koin.android.ext.android.startKoin

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(wordsModule))
  }
}