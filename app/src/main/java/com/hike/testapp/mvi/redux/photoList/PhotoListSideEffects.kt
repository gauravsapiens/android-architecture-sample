package com.hike.testapp.mvi.redux.photoList

import com.hike.testapp.common.model.PhotoRepository
import com.hike.testapp.mvi.core.Action
import com.hike.testapp.mvi.core.SideEffects
import com.hike.testapp.mvi.core.State
import com.hike.testapp.mvi.core.Store
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoListSideEffects : SideEffects {

    override fun runEffects(action: Action, currentState: State, store: Store) {
        when (action) {
            is PhotoListAction.LoadFirstPage -> loadPhotos(store, action.query, 1)
            is PhotoListAction.LoadNextPage ->  {
                val state = currentState as PhotoListState.Default
                loadPhotos(store, state.query, state.page)
            }
        }
    }

    fun loadPhotos(store: Store, query: String, page: Int) {
        PhotoRepository.instance
            .loadPhotos(query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { photoResponse ->
                store.dispatch(
                    PhotoListAction.LoadPhotosSuccess(
                        query,
                        photoResponse.hits,
                        page
                    )
                )
            }
    }
}