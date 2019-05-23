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
            is PhotoListAction.LoadPhotos -> {
                PhotoRepository.instance
                    .loadPhotos("hello", action.page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { photoResponse ->
                        store.dispatch(
                            PhotoListAction.LoadPhotosSuccess(
                                photoResponse.hits,
                                action.page
                            )
                        )
                    }
            }
        }
    }
}