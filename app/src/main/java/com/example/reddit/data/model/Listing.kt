package com.example.reddit.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>
)