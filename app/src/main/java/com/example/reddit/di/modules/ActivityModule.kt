package com.example.reddit.di.modules

import com.example.reddit.ui.main.MainActivity
import com.example.reddit.ui.main.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindManageActivity(): MainActivity
}