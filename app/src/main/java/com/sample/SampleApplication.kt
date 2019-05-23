package com.sample

import android.app.Application
import com.sample.common.model.PhotoRepository
import com.sample.mvi.core.ReduxStore
import com.sample.mvi.redux.Redux
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SampleApplication : Application(){

    override fun onCreate(){
        super.onCreate()

        startKoin {
            androidContext(this@SampleApplication)
            modules(myModule)
        }
    }

    val myModule = module {
        single { PhotoRepository() }
        single { Redux() }
    }

}