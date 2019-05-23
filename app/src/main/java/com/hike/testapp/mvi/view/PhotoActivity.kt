package com.hike.testapp.mvi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hike.testapp.R

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.content_frame,
                PhotoFragment.newInstance("cat")
            )
            .commit()
    }

}
