package com.gney.androidmvvm.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gney.androidmvvm.R
import com.gney.androidmvvm.data.database.entity.Bookmark


class BookmarkAdapter(
    val vm: BookmarkViewModel
) : PagedListAdapter<Bookmark, BookmarkViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bookmark>() {
            override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark) =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bookmark,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        getItem(position)?.run {
            holder.viewDataBinding.item = this
            holder.viewDataBinding.vm = vm
        }
    }
}
