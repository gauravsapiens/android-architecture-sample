package com.hike.testapp.common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hike.testapp.common.model.Photo

class PhotosDiffCallback(val newList: List<Photo>, val oldList: List<Photo>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldItemPosition, newItemPosition)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldList[oldItemPosition]
        val newPhoto = newList[newItemPosition]
        return newPhoto.previewURL.equals(oldPhoto.previewURL)
    }


}