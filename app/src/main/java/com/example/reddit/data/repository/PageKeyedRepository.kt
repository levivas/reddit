package com.example.reddit.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import javax.inject.Inject
import androidx.paging.PagedList
import com.example.reddit.BuildConfig


class PageKeyRepository
@Inject constructor(private val sourceFactory: DataSourceFactory, val config: PagedList.Config) :
    PostRepository {
    @MainThread
    override fun postsReddit(): Listing<Post> {
        val livePagedList = sourceFactory.toLiveData(config)
        return Listing(livePagedList, Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        })
    }
}