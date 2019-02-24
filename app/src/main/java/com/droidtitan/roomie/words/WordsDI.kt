package com.droidtitan.roomie.words

import com.droidtitan.roomie.data.WordRepository
import com.droidtitan.roomie.data.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val wordsModule = module {
  factory { WordRoomDatabase.getDatabase(androidApplication()).wordDao() }
  factory { WordRepository(get()) }
  viewModel { WordsViewModel(Dispatchers.Main, get()) }
}