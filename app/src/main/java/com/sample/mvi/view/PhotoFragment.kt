package com.sample.mvi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.R
import com.sample.common.adapter.PhotosAdapter
import com.sample.mvi.core.State
import com.sample.mvi.core.StateChangeListener
import com.sample.mvi.redux.Redux
import com.sample.mvi.redux.photoList.PhotoListAction
import com.sample.mvi.redux.photoList.PhotoListState
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class PhotoFragment : Fragment(), KoinComponent {

    companion object {

        fun newInstance(searchText: String): PhotoFragment {
            val fragment = PhotoFragment()
            val bundle = bundleOf(Pair("searchText", searchText))
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var photosAdapter: PhotosAdapter
    private val redux by inject<Redux>()

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
        redux.store.subscribe(object : StateChangeListener {
            override fun onUpdate(state: State) {
                onStateUpdated(state)
            }
        })

        //Fire default load action
        val query = arguments?.getString("searchText") ?: ""
        redux.store.dispatch(PhotoListAction.LoadFirstPage(query))
    }

    private fun onStateUpdated(state: State) =
        when (state) {

            is PhotoListState.Loading -> {
                progress_bar.visibility = View.VISIBLE
                recycler_view.visibility = View.GONE
            }

            is PhotoListState.Default -> {
                progress_bar.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
                photosAdapter.photos = state.photos
            }

            else -> {

            }
        }

    private fun setupListPagination() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    redux.store.dispatch(PhotoListAction.LoadNextPage)
                }
            }

        })

    }
}
