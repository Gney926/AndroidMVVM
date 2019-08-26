package com.gney.androidmvvm.ui.search

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.gney.androidmvvm.data.network.response.SearchWebResponse


@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(view: RecyclerView, items: List<SearchWebResponse.Document>, vm: SearchViewModel) {
    view.adapter?.run {
        if (this is SearchAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        SearchAdapter(items, vm).apply { view.adapter = this }
    }
}


@BindingAdapter("setImage")
fun setImage(imageView: ImageView, thumbnail: String) {
    Glide.with(imageView.context).load(thumbnail).placeholder(com.gney.androidmvvm.R.drawable.ic_search_black_24dp).into(imageView)
}


@SuppressLint("SetTextI18n")
@BindingAdapter("setPrice")
fun setPrice(textView: AppCompatTextView, price: String) {
    val p = String.format("%,d", Integer.parseInt(price))
    textView.text = "가격: $p 원"
}


@SuppressLint("SetTextI18n")
@BindingAdapter("setPublish")
fun setPublish(textView: AppCompatTextView, publisher: String) {
    textView.text = "출판: $publisher"
}


@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
    isEnabled = visible
}