package com.example.apiproject.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.R
import com.example.apiproject.model.Photo

class PhotoGridRecyclerViewAdapter : RecyclerView.Adapter<PhotoItemViewHolder>(){


    private var photoList = ArrayList<Photo>()

    // 뷰홀더와 레이아웃 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {

        val photoItemViewHolder = PhotoItemViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_photo_item, parent, false))

        return photoItemViewHolder
    }

    // 보여줄 목록의 갯수
    override fun getItemCount(): Int {
        return this.photoList.size
    }

    // 뷰가 묶였을때 데이터를 뷰홀더에 넘겨준다.
    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {

        holder.bindWithView(this.photoList[position])

    }


    // 외부에서 어답터에 데이터 배열을 넣어준다.
    fun submitList(photoList: ArrayList<Photo>){
        this.photoList = photoList
    }

}