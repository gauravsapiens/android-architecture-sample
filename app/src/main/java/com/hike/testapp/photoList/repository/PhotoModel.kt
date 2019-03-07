package com.hike.testapp.photoList.repository

data class PhotoResponse(val hits: List<Photo>)

data class Photo(val previewURL: String)