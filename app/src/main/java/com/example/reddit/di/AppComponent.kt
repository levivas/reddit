package com.example.reddit.di

import com.example.reddit.RedditApp
import com.example.reddit.di.modules.ActivityModule
import com.example.reddit.di.modules.GeneralModule
import com.example.reddit.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    ActivityModule::class,
    ViewModelModule::class,
    AndroidInjectionModule::class,
    GeneralModule::class])
interface AppComponent : AndroidInjector<RedditApp>