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
import androidx.recyclerview.widget.RecyclerView
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

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    photoViewModel.loadNextPage()
                }
            }
        })

        val query = arguments?.getString("searchText") ?: ""
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        photoViewModel.loadPhotos(query)

        photoViewModel.photosList.observe(this, Observer {
            photosAdapter.photos = it
        })

        photoViewModel.state.observe(this, Observer {
            when (it) {
                State.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }
                State.DEFAULT -> {
                    progress_bar.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                }
                State.PAGINATING -> {

                }
            }
        })
    }
}
