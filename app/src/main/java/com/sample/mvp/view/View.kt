package com.sample.mvp.view

import com.sample.common.model.Photo

interface View {

    fun showLoader()

    fun hideLoader()

    fun appendToList(photos: List<Photo>)

}
