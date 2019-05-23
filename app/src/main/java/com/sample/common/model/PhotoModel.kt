package com.sample.common.model

data class PhotoResponse(val hits: List<Photo>)

data class Photo(val previewURL: String)