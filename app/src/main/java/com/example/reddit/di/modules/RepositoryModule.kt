package com.example.reddit.di.modules

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.reddit.data.model.Post
import com.example.reddit.data.repository.RedditDataSourceFactory
import com.example.reddit.data.repository.RedditPageKeyedDataSource
import com.example.reddit.data.repository.RedditPageKeyedRepository
import com.example.reddit.data.repository.RedditPostRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindDataSourceFactory(dataSourceFactory: RedditDataSourceFactory) : DataSource.Factory<String, Post>

    @Singleton
    @Binds
    abstract fun bindPageKeyedDataSource(dataSourceFactory: RedditPageKeyedDataSource) : PageKeyedDataSource<String, Post>

    @Singleton
    @Binds
    abstract fun bindPageKeyedRepository(pageKeyedRepository: RedditPageKeyedRepository) : RedditPostRepository

}