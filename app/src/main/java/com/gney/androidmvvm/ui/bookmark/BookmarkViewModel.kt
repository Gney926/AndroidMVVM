package com.gney.androidmvvm.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gney.androidmvvm.data.database.dao.BookmarkDao
import com.gney.androidmvvm.data.database.entity.Bookmark
import com.gney.androidmvvm.ui.base.BaseViewModel
import java.util.concurrent.Executors

class BookmarkViewModel(
    private val dao: BookmarkDao
) : BaseViewModel() {
    val items: LiveData<PagedList<Bookmark>> = LivePagedListBuilder(dao.findAll(), 10).build()

    fun delete(bookmark: Bookmark) = Executors.newSingleThreadExecutor().execute { dao.delete(bookmark) }
}