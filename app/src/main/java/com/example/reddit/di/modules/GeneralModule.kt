package com.example.reddit.di.modules

import com.example.reddit.data.remote.WebserviceApi
import com.example.reddit.utils.AppConstants
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides

@Module
class GeneralModule {
    @Provides
    @Singleton
    fun provideRetrofit(): WebserviceApi {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebserviceApi::class.java)
    }
}