package com.droidtitan.roomie.words

import com.droidtitan.roomie.data.WordRepository
import com.droidtitan.roomie.data.WordRoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordsModule = module {
  factory { WordRoomDatabase.getDatabase(androidApplication()).wordDao() }
  factory { WordRepository(get()) }
  viewModel { WordsViewModel(get()) }
}