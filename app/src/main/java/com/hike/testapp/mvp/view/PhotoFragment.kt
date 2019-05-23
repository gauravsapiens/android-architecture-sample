package com.hike.testapp.mvp.view

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
import com.hike.testapp.common.model.Photo
import com.hike.testapp.mvp.presenter.Presenter
import com.hike.testapp.mvp.presenter.PresenterImpl
import kotlinx.android.synthetic.main.fragment_main.*

class PhotoFragment : Fragment(), com.hike.testapp.mvp.view.View {

    companion object {

        fun newInstance(searchText: String): PhotoFragment {
            val fragment = PhotoFragment()
            val bundle = bundleOf(Pair("searchText", searchText)) //Note Extension
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var photosPresenter: Presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Setup recycler view
        photosAdapter = PhotosAdapter(context)
        recycler_view.adapter = photosAdapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        setupListPagination()

        //Setup presenter
        photosPresenter = PresenterImpl()
        photosPresenter.attachView(this)

        //Load photos
        val query = arguments?.getString("searchText") ?: ""
        photosPresenter.loadPhotos(query)
    }

    override fun showLoader() {
        progress_bar.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }

    override fun hideLoader() {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        photosPresenter.detachView()
    }

    override fun appendToList(photos: List<Photo>) {
        photosAdapter.addPhotos(photos)
    }

    fun setupListPagination() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    photosPresenter.loadNextPage()
                }
            }
        })

    }
}
