package com.gney.androidmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gney.androidmvvm.data.database.BookmarkDB.Companion.DB_VERSION
import com.gney.androidmvvm.data.database.dao.BookmarkDao
import com.gney.androidmvvm.data.database.entity.Bookmark


@Database(entities = [Bookmark::class], version = DB_VERSION, exportSchema = false)
abstract class BookmarkDB: RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "bookmark.db"

        private var INSTANCE: BookmarkDB? = null

        fun getInstance(context: Context): BookmarkDB? {
            if (INSTANCE == null) {
                synchronized(BookmarkDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }


        fun destroyInstance() {
            INSTANCE = null
        }
    }
}