package com.sample.photolist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.common.model.Photo
import com.sample.common.model.PhotoRepository
import com.sample.mvvm.viewmodel.PhotoViewModel
import com.sample.mvvm.viewmodel.State
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PhotoViewModelTest {

    @Mock
    lateinit var photoRepository: PhotoRepository
    lateinit var photoViewModel: PhotoViewModel
    lateinit var observer: Observer<MutableList<Photo>>

    @Rule
    lateinit var instantTaskExecutorRule: InstantTaskExecutorRule


    @Before
    fun setupViewModel() {
        MockitoAnnotations.initMocks(this)
        photoViewModel = PhotoViewModel(photoRepository)
        observer = Observer { }
        instantTaskExecutorRule= InstantTaskExecutorRule()
    }

    @Test
    fun loadPhotos() {
        photoViewModel.loadPhotos("Hello")
        assert(photoViewModel.state.value == State.LOADING)
    }

    @Test
    fun loadMorePhotos() {
        photoViewModel.loadPhotos("Hello")
        photoViewModel.loadNextPage()
        assert(photoViewModel.state.value == State.PAGINATING)
    }

}