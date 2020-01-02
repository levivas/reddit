package com.example.reddit.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import javax.inject.Inject

class PageKeyRepository
@Inject constructor(private val api: WebserviceApi) : PostRepository {
    @MainThread
    override fun postsReddit(pageSize: Int): Listing<Post> {
        val sourceFactory = DataSourceFactory(api)
        val livePagedList = sourceFactory.toLiveData(pageSize = pageSize)
        return Listing(livePagedList, Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        })
    }
}