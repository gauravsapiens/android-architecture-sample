package com.hike.testapp.mvp.presenter

import com.hike.testapp.mvp.view.View

interface Presenter {

    fun loadPhotos(query: String)

    fun loadNextPage()

    fun attachView(view: View)

    fun detachView()

}