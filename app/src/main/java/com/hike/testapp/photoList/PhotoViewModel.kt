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
    val photosList = MutableLiveData<List<Photo>>()

    fun loadPhotos(query: String) {
        photoRepository.loadPhotos(query, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> photosList.value = result.hits }, { it.printStackTrace() })
    }

}