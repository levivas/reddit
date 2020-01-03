package com.example.reddit.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import javax.inject.Inject

class DataSourceFactory
    @Inject constructor(private val webApi: WebserviceApi) : DataSource.Factory<String, Post>() {

    val sourceLiveData = MutableLiveData<PageKeyedDataSource>()

    override fun create(): DataSource<String, Post> {
        val source = PageKeyedDataSource(webApi)
        sourceLiveData.postValue(source)
        return source
    }
}