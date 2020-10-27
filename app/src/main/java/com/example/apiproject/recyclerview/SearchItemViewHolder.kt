package com.example.apiproject.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.model.SearchData
import kotlinx.android.synthetic.main.layout_search_item.view.*

class SearchItemViewHolder(itemView: View, searchRecyclerViewInterface:SearchHistoryRecyclerView) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mySearchRecyclerViewInterface: SearchHistoryRecyclerView

    private val searchTermTextView = itemView.search_term_text
    private val whenSearchedTextView = itemView.when_searched_text
    private val deleteSearchButton = itemView.delete_search_button
    private val constraintSearchItem = itemView.constraint_search_item

    init {

        deleteSearchButton.setOnClickListener(this)
        constraintSearchItem.setOnClickListener(this)
        this.mySearchRecyclerViewInterface = searchRecyclerViewInterface
    }
    fun bindWithView(searchItem: SearchData) {
        whenSearchedTextView.text = searchItem.timestamp

        searchTermTextView.text =searchItem.term
    }

    override fun onClick(view: View?) {
        when(view) {
            deleteSearchButton -> {
                this.mySearchRecyclerViewInterface.onSearchItemDeleteClicked(adapterPosition)
            }
            constraintSearchItem -> {
                this.mySearchRecyclerViewInterface.onSearchItemClicked(adapterPosition)
            }
        }
    }
}