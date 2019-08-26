package com.gney.androidmvvm.di

import com.gney.androidmvvm.data.database.BookmarkDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        BookmarkDB.getInstance(androidApplication())
    }


    single(createdAtStart = false) {
        get<BookmarkDB>().bookmarkDao()
    }
}