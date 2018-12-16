package com.droidtitan.wordsample.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

  abstract fun wordDao(): WordDao

  companion object {

    @Volatile private var INSTANCE: WordRoomDatabase? = null

    fun getDatabase(context: Context): WordRoomDatabase =
        INSTANCE
          ?: synchronized(this) { INSTANCE
            ?: buildDatabase(context).also { INSTANCE = it } }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database")
            .addCallback(object : RoomDatabase.Callback() {
              override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.wordDao()?.apply {
                  CoroutineScope(Dispatchers.IO).launch {
                    deleteAll()
                    val words = arrayOf("dolphin", "crocodile", "cobra")
                    words.forEach { this@apply.insert(Word(it)) }
                  }
                }
              }
            })
            // Wipes and rebuilds instead of migrating
            .fallbackToDestructiveMigration()
            .build()
  }
}