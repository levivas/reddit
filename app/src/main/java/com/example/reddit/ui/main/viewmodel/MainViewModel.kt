package com.example.reddit.ui.main.viewmodel

import com.example.reddit.data.repository.PageKeyRepository
import com.example.reddit.ui.base.BaseViewModel
import com.example.reddit.utils.AppConstants
import javax.inject.Inject

class MainViewModel
@Inject constructor(repository: PageKeyRepository) : BaseViewModel() {
    val postLiveData = repository.postsReddit(AppConstants.PartCountPost).pagedList
}