package com.hike.testapp.mvi.redux.photoList

import com.hike.testapp.common.model.Photo
import com.hike.testapp.mvi.core.Action

sealed class PhotoListAction : Action {

    data class LoadPhotos(val page: Int) : PhotoListAction()
    data class LoadPhotosSuccess(val photos: List<Photo>, val page: Int) : PhotoListAction()
    data class LoadPhotosFailure(val error: Throwable) : PhotoListAction()

}
