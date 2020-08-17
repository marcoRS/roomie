package com.droidtitan.roomie

import android.app.Application
import com.droidtitan.roomie.words.wordsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@App)
      modules(wordsModule)
    }
  }
}