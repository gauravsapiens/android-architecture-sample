package com.sample.mvi.redux.photoList

import com.sample.common.model.PhotoRepository
import com.sample.mvi.core.Action
import com.sample.mvi.core.SideEffects
import com.sample.mvi.core.State
import com.sample.mvi.core.Store
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