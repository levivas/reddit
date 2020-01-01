package com.example.reddit.ui.main

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.BR
import com.example.reddit.R
import com.example.reddit.data.model.Post
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.ui.base.BaseActivity
import com.example.reddit.ui.main.adapter.PostAdapter
import com.example.reddit.ui.main.viewmodel.MainViewModel
import com.example.reddit.utils.viewmodel.viewModelProvider

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
//        val dividerItemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
//        recyclerView.addItemDecoration(dividerItemDecoration)
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
    }

    private fun openDetail(url: String){
        val builder = CustomTabsIntent.Builder()
        builder.addDefaultShareMenuItem()
        builder.setShowTitle(true)
        // animation for enter and exit of tab
        builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        val customTabsIntent = builder.build()
        customTabsIntent.intent.setPackage(packageName)
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}