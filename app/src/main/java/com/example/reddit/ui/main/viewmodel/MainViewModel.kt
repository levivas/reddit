package com.example.reddit.ui.main.viewmodel

import androidx.databinding.ObservableBoolean
import com.example.reddit.data.repository.PageKeyRepository
import com.example.reddit.ui.base.BaseViewModel
import com.example.reddit.utils.AppConstants
import javax.inject.Inject

class MainViewModel
@Inject constructor(repository: PageKeyRepository) : BaseViewModel() {
    val isLoading = ObservableBoolean(true)
    var result = repository.postsReddit()
    val postLiveData = result.pagedList
    val networkLiveData = result.networkState
}