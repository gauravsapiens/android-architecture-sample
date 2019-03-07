package com.hike.testapp.photoList.repository

import io.reactivex.Observable

class PhotoRepository {

    companion object {
        val instance = PhotoRepository()
    }

    private val photoService = PhotoService.create()

    fun loadPhotos(query: String, page: Int): Observable<PhotoResponse> {
        return photoService.getPhotos(query, page)
    }

}