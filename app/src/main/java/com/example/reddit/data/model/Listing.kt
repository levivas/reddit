package com.example.reddit.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,

    val networkState: LiveData<NetworkState>
)