package com.sample.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.mvvm.viewmodel.PhotoViewModel
import com.sample.common.model.PhotoRepository

class PhotoViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (PhotoViewModel(PhotoRepository.instance) as T)
    }

}