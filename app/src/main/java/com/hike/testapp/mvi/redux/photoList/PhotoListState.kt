package com.hike.testapp.mvi.redux.photoList

import com.hike.testapp.common.model.Photo
import com.hike.testapp.mvi.core.State

sealed class PhotoListState : State() {

    object Loading : PhotoListState()

    data class Default(val page: Int, val photos: List<Photo>) : PhotoListState()

    object Failure : PhotoListState()

}
