package com.example.reddit.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.reddit.data.model.NetworkState
import com.example.reddit.data.model.Post
import com.example.reddit.data.remote.WebserviceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyedDataSource(private val redditApi: WebserviceApi) : PageKeyedDataSource<String, Post>() {

    val networkState = MutableLiveData<NetworkState>()

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Post>) {
        networkState.postValue(NetworkState.LOADING)
        redditApi.getTop(params.requestedLoadSize)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.data.children.map { it.data }, it.data.before, it.data.after)
            }, {
                Log.e("PageKeyDataSource", "redditApi.getTop()")
                networkState.value = NetworkState.error(it.message)
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
                networkState.value = NetworkState.error(it.message)
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
    }
}