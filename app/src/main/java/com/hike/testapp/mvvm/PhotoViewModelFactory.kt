package com.hike.testapp.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hike.testapp.mvvm.viewmodel.PhotoViewModel
import com.hike.testapp.common.model.PhotoRepository

class PhotoViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (PhotoViewModel(PhotoRepository.instance) as T)
    }

}