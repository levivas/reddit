package com.example.reddit.ui.main.di

import androidx.lifecycle.ViewModel
import com.example.reddit.data.repository.PageKeyRepository
import com.example.reddit.data.repository.PostRepository
import com.example.reddit.ui.main.viewmodel.MainViewModel
import com.example.reddit.utils.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

    internal abstract fun bindRepo(repository: PageKeyRepository): PostRepository
}