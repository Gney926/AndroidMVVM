package com.gney.androidmvvm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gney.androidmvvm.data.database.dao.BookmarkDao
import com.gney.androidmvvm.data.database.entity.Bookmark
import com.gney.androidmvvm.data.network.KakaoClient
import com.gney.androidmvvm.data.network.response.SearchWebResponse
import com.gney.androidmvvm.ui.base.BaseViewModel
import com.gney.androidmvvm.util.SingleLiveEvent
import com.gney.androidmvvm.util.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors


class SearchViewModel(
    private val kakaoClient: KakaoClient,
    private val bookmarkDao: BookmarkDao
) : BaseViewModel() {

    private var query: String = ""
        get() = if (field.isEmpty()) "" else field


    private val _refreshing = MutableLiveData<Boolean>().default(false)
    val refreshing: LiveData<Boolean>
        get() = _refreshing


    private val _items = MutableLiveData<List<SearchWebResponse.Document>>().default(arrayListOf())
    val items: LiveData<List<SearchWebResponse.Document>>
        get() = _items


    val observableEvent = SingleLiveEvent<Status>()


    fun doSearch() {
        addDisposable(kakaoClient.getWebData(query, "Accuracy", 1, 30, "title")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doOnSuccess { hideProgress() }
            .doOnError { hideProgress() }
            .subscribe({
                _items.value = it.documents
            }, {
                // handle errors
            })
        )
    }


    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }


    private fun showProgress() {
        _refreshing.value = true
    }


    private fun hideProgress() {
        _refreshing.value = false
    }


    private fun <T : Any?> MutableLiveData<T>.default(initialValue: T) =
        apply { setValue(initialValue) }


    fun saveToBookmark(document: SearchWebResponse.Document) {
        observableEvent.value = Status.SAVE

        Executors.newSingleThreadExecutor().execute {
            bookmarkDao.insert(Bookmark.to(document))
        }
    }

}