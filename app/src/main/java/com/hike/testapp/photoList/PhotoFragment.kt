package com.hike.testapp.photoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hike.testapp.R
import kotlinx.android.synthetic.main.fragment_main.*

class PhotoFragment : Fragment() {

    companion object {

        fun newInstance(searchText: String): PhotoFragment {
            val fragment = PhotoFragment()
            val bundle = bundleOf(Pair("searchText", searchText)) //Note Extension
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photosAdapter = PhotosAdapter(context)
        recycler_view.adapter = photosAdapter
        recycler_view.layoutManager = LinearLayoutManager(context)

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        photoViewModel.loadPhotos("Hello")

        photoViewModel.photosList.observe(this, Observer {
            photosAdapter.photos = it
            photosAdapter.notifyDataSetChanged()
        })
    }
}
