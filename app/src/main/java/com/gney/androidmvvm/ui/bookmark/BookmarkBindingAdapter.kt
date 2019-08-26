package com.gney.androidmvvm.ui.bookmark

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gney.androidmvvm.R
import com.gney.androidmvvm.data.database.entity.Bookmark

@BindingAdapter(value = ["bookmarks", "viewModel"])
fun setBookmarks(view: RecyclerView, items: PagedList<Bookmark>?, vm: BookmarkViewModel) {
    view.adapter?.run {
        if (this is BookmarkAdapter) this.submitList(items)
    } ?: run {
        BookmarkAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, thumbnail: String) {
    Glide.with(imageView.context).load(thumbnail).placeholder(R.drawable.ic_search_black_24dp).into(imageView)
}


@SuppressLint("SetTextI18n")
@BindingAdapter("loadPrice")
fun loadPrice(textView: AppCompatTextView, price: String) {
    val p = String.format("%,d", Integer.parseInt(price))
    textView.text = "가격: $p 원"
}


@SuppressLint("SetTextI18n")
@BindingAdapter("loadPublish")
fun loadPublish(textView: AppCompatTextView, publisher: String) {
    textView.text = "출판: $publisher"
}