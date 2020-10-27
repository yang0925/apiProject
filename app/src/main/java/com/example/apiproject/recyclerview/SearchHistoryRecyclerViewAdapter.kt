package com.example.apiproject.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.R
import com.example.apiproject.model.SearchData

class SearchHistoryRecyclerViewAdapter(searchHistoryRecyclerViewInterface: SearchHistoryRecyclerView) : RecyclerView.Adapter<SearchItemViewHolder>(){

    private var searchHistoryList: ArrayList<SearchData> = ArrayList()
    private var searchHistoryRecyclerView: SearchHistoryRecyclerView? = null

    init {
        this.searchHistoryRecyclerView = searchHistoryRecyclerViewInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_search_item, parent, false),
            this.searchHistoryRecyclerView!!
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val dataItem: SearchData = this.searchHistoryList[position]
        holder.bindWithView(dataItem)
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    fun submitList(searchHistoryList: ArrayList<SearchData>){
        this.searchHistoryList = searchHistoryList
    }

}