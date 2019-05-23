package com.hike.testapp.mvi.redux

import com.hike.testapp.mvi.core.ReduxStore
import com.hike.testapp.mvi.redux.photoList.PhotoListReducer
import com.hike.testapp.mvi.redux.photoList.PhotoListSideEffects
import com.hike.testapp.mvi.redux.photoList.PhotoListState

class Redux {

    companion object {
        val INSTANCE = Redux()
    }

    val store = ReduxStore(PhotoListState.Loading, listOf(PhotoListReducer()), listOf(PhotoListSideEffects()))

}