package com.sample.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.R
import com.sample.common.model.Photo
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var photos: List<Photo> = listOf()
        set(value) {
            val oldList = field
            field = value
            refreshList(value, oldList)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit =
        Glide.with(context!!).load(photos[position].previewURL).into(holder.itemView.image).let { return }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun refreshList(newList: List<Photo>, oldList: List<Photo>) {
        val diffResult = DiffUtil.calculateDiff(PhotosDiffCallback(newList, oldList))
        diffResult.dispatchUpdatesTo(this)
    }

    fun addPhotos(photoList: List<Photo>) {
        val _photos = mutableListOf<Photo>()
        _photos.apply {
            addAll(photos)
            addAll(photoList)
        }
        photos = _photos
    }
}
