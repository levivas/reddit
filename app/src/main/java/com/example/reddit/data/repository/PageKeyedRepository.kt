package com.example.reddit.data.repository

import androidx.annotation.MainThread
import androidx.paging.toLiveData
import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import javax.inject.Inject

class PageKeyRepository
@Inject constructor() : PostRepository {
    @MainThread
    override fun postsReddit(pageSize: Int): Listing<Post> {
        val sourceFactory = DataSourceFactory(WebserviceApi.create())//add dagger dependency
        val livePagedList = sourceFactory.toLiveData(pageSize = pageSize)
        return Listing(pagedList = livePagedList)
    }
}