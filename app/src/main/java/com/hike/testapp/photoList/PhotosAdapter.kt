package com.hike.testapp.photoList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hike.testapp.R
import com.hike.testapp.photoList.repository.Photo
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var photos: List<Photo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged() //TODO: Add diff util
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit =
        Glide.with(context!!).load(photos[position].previewURL).into(holder.itemView.image).let { return }

    override fun getItemCount(): Int {
        return photos.size
    }

}
