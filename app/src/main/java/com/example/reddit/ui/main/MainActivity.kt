package com.example.reddit.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.BR
import com.example.reddit.R
import com.example.reddit.data.model.NetworkState
import com.example.reddit.data.model.Post
import com.example.reddit.data.model.Status
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.ui.base.BaseActivity
import com.example.reddit.ui.main.adapter.PostAdapter
import com.example.reddit.ui.main.viewmodel.MainViewModel
import com.example.reddit.utils.viewModelProvider

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main


    override fun provideViewModel(): MainViewModel = viewModelProvider(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    /**
     * Set up ui elements
     */
    private fun setupUI() {
        val recyclerView = viewDataBinding!!.postListRecyclerView
        val adapter = PostAdapter()
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = adapter
        viewModel.postLiveData.observe(this, Observer<PagedList<Post>> {
            adapter.submitList(it) {
                // Workaround for an issue where RecyclerView incorrectly uses the loading / spinner
                // item added to the end of the list as an anchor during initial load.
                val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (position != RecyclerView.NO_POSITION) {
                    recyclerView.scrollToPosition(position)
                }
            }
        })

        viewModel.networkLiveData.observe(this, Observer<NetworkState> {
            if(it.status == Status.FAILED){
                Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
            }
            viewModel.isLoading.set(it == NetworkState.LOADING)
        })
    }
}