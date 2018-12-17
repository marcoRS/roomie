package com.droidtitan.roomie.data

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

    private fun buildDatabase(ctx: Context) =
      Room.databaseBuilder(ctx.applicationContext, WordRoomDatabase::class.java, "word_database")
        .build()
  }
}