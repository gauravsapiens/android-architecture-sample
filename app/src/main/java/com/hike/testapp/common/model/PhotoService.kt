package com.hike.testapp.common.model

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotoService {

    companion object {
        fun create(): PhotoService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/api/")
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build()

            return retrofit.create(PhotoService::class.java)
        }
    }

    @GET("?key=11688696-d01419a92dd01e3a99f5611fe")
    fun getPhotos(@Query("q") q: String, @Query("page") page: Int): Observable<PhotoResponse>

}
