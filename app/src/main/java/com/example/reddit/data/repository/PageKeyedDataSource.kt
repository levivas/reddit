package com.example.reddit.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.reddit.data.model.DataPost
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class PageKeyedDataSource(private val redditApi: WebserviceApi) :
    PageKeyedDataSource<String, Post>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Post>
    ) {
        redditApi.getTop(params.requestedLoadSize)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it.data.children.map { it.data }, it.data.before, it.data.after)
            }, {
                Log.e("PageKeyDataSource", "redditApi.getTop()")
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

        redditApi.getTopAfter(params.key, params.requestedLoadSize)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it.data.children.map { it.data }, it.data.after)
            }, {
                Log.e("PageKeyDataSource", "redditApi.getTopAfter()")
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
    }
}