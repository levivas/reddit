package com.example.reddit.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.reddit.utils.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}