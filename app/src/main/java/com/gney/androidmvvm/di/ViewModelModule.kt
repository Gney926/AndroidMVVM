package com.gney.androidmvvm.di

import com.gney.androidmvvm.ui.bookmark.BookmarkViewModel
import com.gney.androidmvvm.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
    viewModel {
        SearchViewModel(get(), get())
    }

    viewModel {
        BookmarkViewModel(get())
    }
}