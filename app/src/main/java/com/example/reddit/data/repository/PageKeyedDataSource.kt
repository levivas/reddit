package com.example.reddit.data.repository

import androidx.paging.PageKeyedDataSource
import com.example.reddit.data.model.DataPost
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import retrofit2.Call
import retrofit2.Response

class PageKeyedDataSource(private val redditApi: WebserviceApi) : PageKeyedDataSource<String, Post>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Post>) {
        val request = redditApi.getTop(params.requestedLoadSize)
        val response = request.execute()
        val data = response.body()?.data
        val items = data?.children!!.map { it.data }
        callback.onResult(items, data.before, data.after)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

        redditApi.getTopAfter(params.key, params.requestedLoadSize).enqueue(
            object : retrofit2.Callback<DataPost> {
                override fun onResponse(call: Call<DataPost>, response: Response<DataPost>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        val items = data?.children!!.map { it.data }
                        callback.onResult(items, data.after)
                    }
                }

                override fun onFailure(call: Call<DataPost>, t: Throwable) {
                }
            }
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
    }
}