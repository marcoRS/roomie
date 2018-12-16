package com.droidtitan.wordsample

import com.droidtitan.wordsample.data.WordRepository
import com.droidtitan.wordsample.data.WordRoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val wordsModule = module {
  factory { WordRoomDatabase.getDatabase(androidApplication()).wordDao() }
  factory { WordRepository(get()) }
  viewModel { WordsViewModel(get()) }
}