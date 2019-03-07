package com.hike.testapp.photoList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hike.testapp.photoList.repository.Photo
import com.hike.testapp.photoList.repository.PhotoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoViewModel(application: Application) : AndroidViewModel(application) {

    private val photoRepository = PhotoRepository.instance
    private lateinit var query: String
    private var page = 1

    val photosList = MutableLiveData<MutableList<Photo>>()

    fun loadPhotos(query: String) {
        this.query = query
        photoRepository.loadPhotos(query, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> photosList.value = ArrayList<Photo>().apply { addAll(result.hits) } },
                { it.printStackTrace() }
            )
    }

    fun loadNextPage() {
        photoRepository.loadPhotos(query, ++page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> photosList.value = photosList.value.apply { this!!.addAll(result.hits) } },
                { it.printStackTrace() }
            )
    }

}