package com.example.apiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apiproject.model.Photo
import com.example.apiproject.recyclerview.PhotoGridRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_photo_collection.*

class PhotoCollectionActivity: AppCompatActivity() {


    // 데이터
    private var photoList = ArrayList<Photo>()

    // 어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("array_bundle")

        val searchTerm = intent.getStringExtra("search_term")

        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        topAppBar.title = searchTerm


        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()

        this.photoGridRecyclerViewAdapter.submitList(photoList)



        my_photo_recycler_view.layoutManager = GridLayoutManager(this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        my_photo_recycler_view.adapter = this.photoGridRecyclerViewAdapter


    } //

}