package com.gney.androidmvvm.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gney.androidmvvm.R
import com.gney.androidmvvm.data.network.response.SearchWebResponse

class SearchAdapter(
    var items: List<SearchWebResponse.Document> = arrayListOf(),
    private val searchViewModel: SearchViewModel
) : RecyclerView.Adapter<SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.viewDataBinding.item = items[position]
        holder.viewDataBinding.vm = searchViewModel
    }


    override fun getItemCount(): Int = items.size
}

