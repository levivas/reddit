package com.example.reddit.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post
import javax.inject.Inject
import androidx.paging.PagedList


class RedditPageKeyedRepository
@Inject constructor(private val sourceFactory: RedditDataSourceFactory, val config: PagedList.Config) :
    RedditPostRepository {
    @MainThread
    override fun postsReddit(): Listing<Post> {
        val livePagedList = sourceFactory.toLiveData(config)
        return Listing(livePagedList, Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        })
    }
}