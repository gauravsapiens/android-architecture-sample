package com.hike.testapp.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hike.testapp.R
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testRx()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_frame,
                PhotoFragment.newInstance("Hello")
            )
            .commit()
    }

    fun testRx() {

        //Observable
        var i = 0
        val observable = Observable.create<Int> { emitter ->
            (1..100).forEach {
                i++
                if (it % 2 == 0) {
                    emitter.onNext(it)
                }
            }
        }

        (1..4).forEach { index ->
            observable.subscribe(Consumer { Log.d("RX Observable ${index}", "${it}") })
        }

        Log.d("RX Observable Count:", "${i}")


        //Publish subject
        var j = 0
        val publishSubject = PublishSubject.create<Int> ()
        (1..4).forEach { index ->
            publishSubject.subscribe({ Log.d("RX PublishSubject ${index}", "${it}") })
        }

        (1..100).forEach {
            j++
            if (it % 2 == 0) {
                publishSubject.onNext(it)
            }
        }


        Log.d("RX PublishSub Count:", "${j}")

    }
}
