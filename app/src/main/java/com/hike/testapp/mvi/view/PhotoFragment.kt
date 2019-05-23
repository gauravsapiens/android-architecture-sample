package com.hike.testapp.mvi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hike.testapp.R
import com.hike.testapp.common.adapter.PhotosAdapter
import com.hike.testapp.mvi.core.State
import com.hike.testapp.mvi.core.StateChangeListener
import com.hike.testapp.mvi.redux.Redux
import com.hike.testapp.mvi.redux.photoList.PhotoListAction
import com.hike.testapp.mvi.redux.photoList.PhotoListState
import kotlinx.android.synthetic.main.fragment_main.*

class PhotoFragment : Fragment() {

    companion object {

        fun newInstance(searchText: String): PhotoFragment {
            val fragment = PhotoFragment()
            val bundle = bundleOf(Pair("searchText", searchText))
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photosAdapter = PhotosAdapter(context)
        recycler_view.adapter = photosAdapter
        recycler_view.layoutManager = LinearLayoutManager(context)

        setupListPagination()

        //Subscribe to state changes
        Redux.INSTANCE.store.subscribe(object : StateChangeListener {
            override fun onUpdate(state: State) {
                onStateUpdated(state)
            }
        })

        val query = arguments?.getString("searchText") ?: ""

        //Fire default load action
        Redux.INSTANCE.store.dispatch(PhotoListAction.LoadPhotos(1))
    }

    private fun onStateUpdated(state: State) =
        when (state) {
            PhotoListState.Loading -> {
                progress_bar.visibility = View.VISIBLE
                recycler_view.visibility = View.GONE
            }

            is PhotoListState.Default -> {
                progress_bar.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
                photosAdapter.photos = state.photos
                photosAdapter.notifyDataSetChanged()
            }

            is PhotoListState.Failure -> {
                progress_bar.visibility = View.VISIBLE
                recycler_view.visibility = View.GONE
            }

            else -> {

            }
        }

    private fun setupListPagination() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    PhotoListAction.LoadPhotos(1)
                }
            }

        })

    }
}
