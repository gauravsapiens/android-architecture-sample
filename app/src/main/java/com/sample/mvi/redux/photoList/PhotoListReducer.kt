package com.sample.mvi.redux.photoList

import com.sample.common.model.Photo
import com.sample.mvi.core.Action
import com.sample.mvi.core.Reducer
import com.sample.mvi.core.State

class PhotoListReducer : Reducer {

    override fun reduce(state: State, action: Action): State {
        return when (action) {

            is PhotoListAction.LoadFirstPage -> PhotoListState.Loading(action.query, 1)

            is PhotoListAction.LoadNextPage -> {
                val currentState = state as PhotoListState.Default
                return PhotoListState.Default(currentState.query, currentState.page + 1, currentState.photos)
            }

            is PhotoListAction.LoadPhotosSuccess -> {
                val currentPhotoList = if (state is PhotoListState.Default) {
                    state.photos
                } else {
                    emptyList()
                }
                val photos = mutableListOf<Photo>()
                photos.apply {
                    addAll(currentPhotoList)
                    addAll(action.photos)
                }

                return PhotoListState.Default(
                    query = action.query,
                    page = action.page,
                    photos = photos
                )
            }

            is PhotoListAction.LoadPhotosFailure -> PhotoListState.Failure

            else -> state
        }
    }
}