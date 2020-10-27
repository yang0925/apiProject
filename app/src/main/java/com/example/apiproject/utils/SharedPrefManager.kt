package com.example.apiproject.utils

import android.content.Context
import com.example.apiproject.App
import com.example.apiproject.model.SearchData
import com.google.gson.Gson

object SharedPrefManager {
    private const val SHARED_SEARCH_HISTORY = "shared_search_history"
    private const val KEY_SEARCH_HISTORY = "key_search_history"

    fun storeSearchHistoryList(searchHistoryList: MutableList<SearchData>) {
        val searchHistoryListString : String = Gson().toJson(searchHistoryList)
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val editor = shared.edit()

        editor.putString(KEY_SEARCH_HISTORY, searchHistoryListString)
        editor.apply()
    }

    fun getSearchHistoryList() : MutableList<SearchData>{
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val storedSearchHistoryListString = shared.getString(KEY_SEARCH_HISTORY, "")!!
        var storedSearchHistoryList = ArrayList<SearchData>()

        if(storedSearchHistoryListString.isNotEmpty()) {
            storedSearchHistoryList = Gson().fromJson(storedSearchHistoryListString, Array<SearchData>::class.java).toMutableList() as ArrayList<SearchData>
        }
        return storedSearchHistoryList
    }
}