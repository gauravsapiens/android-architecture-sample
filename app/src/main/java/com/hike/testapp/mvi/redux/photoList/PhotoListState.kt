package com.hike.testapp.mvi.redux.photoList

import com.hike.testapp.common.model.Photo
import com.hike.testapp.mvi.core.State

sealed class PhotoListState : State() {

    data class Loading(val query: String, val page: Int) : PhotoListState()

    data class Default(val query: String, val page: Int, val photos: List<Photo>) : PhotoListState()

    object Failure : PhotoListState()

}
