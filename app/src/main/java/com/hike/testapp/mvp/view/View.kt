package com.hike.testapp.mvp.view

import com.hike.testapp.common.model.Photo

interface View {

    fun showLoader()

    fun hideLoader()

    fun appendToList(photos: List<Photo>)

}
