package com.example.reddit.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.reddit.data.model.Post
import javax.inject.Inject

class RedditDataSourceFactory
    @Inject constructor(private val source: RedditPageKeyedDataSource) : DataSource.Factory<String, Post>() {

    val sourceLiveData = MutableLiveData<RedditPageKeyedDataSource>()

    override fun create(): DataSource<String, Post> {
        sourceLiveData.postValue(source)
        return source
    }
}