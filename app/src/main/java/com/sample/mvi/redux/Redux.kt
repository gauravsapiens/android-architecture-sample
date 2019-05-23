package com.sample.mvi.redux

import com.sample.mvi.core.ReduxStore
import com.sample.mvi.redux.photoList.PhotoListReducer
import com.sample.mvi.redux.photoList.PhotoListSideEffects
import com.sample.mvi.redux.photoList.PhotoListState

class Redux {

    companion object {
        val INSTANCE = Redux()
    }

    val store = ReduxStore(PhotoListState.Loading("", 1), listOf(PhotoListReducer()), listOf(PhotoListSideEffects()), emptyList())

}