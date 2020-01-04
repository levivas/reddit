package com.example.reddit.ui.main.viewmodel

import androidx.databinding.ObservableBoolean
import com.example.reddit.data.repository.RedditPageKeyedRepository
import com.example.reddit.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(repository: RedditPageKeyedRepository) : BaseViewModel() {
    val isLoading = ObservableBoolean(true)
    var result = repository.postsReddit()
    val postLiveData = result.pagedList
    val networkLiveData = result.networkState
}