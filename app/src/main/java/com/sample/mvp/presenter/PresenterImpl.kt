package com.sample.mvp.presenter

import com.sample.common.model.PhotoRepository
import com.sample.mvp.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class PresenterImpl : Presenter, KoinComponent {

    val photoRepository by inject<PhotoRepository>()
    var view: View? = null
    var query = ""
    var page = 1

    override fun loadPhotos(query: String) {
        this.query = query
        view?.showLoader()
        loadPhotos()
    }

    override fun loadNextPage() {
        this.page = page + 1
        loadPhotos()
    }

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun loadPhotos() {
        photoRepository.loadPhotos(query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view?.hideLoader()
                    view?.appendToList(result.hits)
                },
                { it.printStackTrace() }
            )
    }
}