package com.sample.mvp.presenter

import com.sample.mvp.view.View

interface Presenter {

    fun loadPhotos(query: String)

    fun loadNextPage()

    fun attachView(view: View)

    fun detachView()

}