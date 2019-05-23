package com.sample.mvi.redux.photoList

import com.sample.common.model.Photo
import com.sample.mvi.core.State

sealed class PhotoListState : State() {

    data class Default(val query: String, val page: Int, val photos: List<Photo>) : PhotoListState()

    data class Loading(val query: String, val page: Int) : PhotoListState()

    object Failure : PhotoListState()

}
