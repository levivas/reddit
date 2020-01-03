package com.example.reddit.di.modules

import androidx.paging.PagedList
import com.example.reddit.data.remote.WebserviceApi
import com.example.reddit.utils.AppConstants
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class GeneralModule {
    @Provides
    @Singleton
    fun provideRetrofit(): WebserviceApi {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebserviceApi::class.java)
    }

    @Provides
    @Singleton
    fun providePagedListConfigBuilder() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPrefetchDistance(AppConstants.PrefetchSize)
        .setPageSize(AppConstants.PageSize)
        .build()
}