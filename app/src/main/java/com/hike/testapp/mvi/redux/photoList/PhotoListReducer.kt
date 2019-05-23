package com.hike.testapp.mvi.redux.photoList

import com.hike.testapp.mvi.core.Action
import com.hike.testapp.mvi.core.Reducer
import com.hike.testapp.mvi.core.State

class PhotoListReducer : Reducer {

    override fun reduce(state: State, action: Action): State {
        return when (action) {
            is PhotoListAction.LoadPhotos -> PhotoListState.Loading
            is PhotoListAction.LoadPhotosSuccess -> PhotoListState.Default(
                page = action.page,
                photos = action.photos
            )
            is PhotoListAction.LoadPhotosFailure -> PhotoListState.Failure
            else -> state
        }
    }
}