package com.sample.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.common.model.PhotoRepository
import com.sample.mvvm.viewmodel.PhotoViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoViewModelFactory : ViewModelProvider.Factory, KoinComponent {

    val photoRepository by inject<PhotoRepository>()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return (PhotoViewModel(photoRepository) as T)
    }

}