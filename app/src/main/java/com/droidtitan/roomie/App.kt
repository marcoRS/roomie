package com.droidtitan.roomie

import android.app.Application
import com.droidtitan.roomie.data.WordRepository
import com.droidtitan.roomie.data.WordRoomDatabase
import com.droidtitan.roomie.words.WordsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

  private val wordsModule = module {
    factory { WordRoomDatabase.getDatabase(androidApplication()).wordDao() }
    factory { WordRepository(get()) }
    viewModel { WordsViewModel(get()) }
  }

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@App)
      modules(wordsModule)
    }
  }
}