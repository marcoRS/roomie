package com.droidtitan.wordsample

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

  abstract fun wordDao(): WordDao

  companion object {

    @Volatile private var INSTANCE: WordRoomDatabase? = null

    fun getDatabase(context: Context): WordRoomDatabase =
        INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also { INSTANCE = it } }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            WordRoomDatabase::class.java, "word_database")
            // Wipes and rebuilds instead of migrating
            .fallbackToDestructiveMigration()
            .build()
  }
}