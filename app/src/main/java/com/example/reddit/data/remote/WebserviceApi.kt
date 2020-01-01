package com.example.reddit.data.remote

import android.util.Log
import com.example.reddit.data.model.DataPost
import com.example.reddit.utils.AppConstants
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebserviceApi {

    @GET("top.json")
    fun getTop(
        @Query("limit") limit: Int): Call<DataPost>

    @GET("top.json")
    fun getTopAfter(
        @Query("after") after: String,
        @Query("limit") limit: Int): Call<DataPost>


    companion object {
        fun create(): WebserviceApi = create(HttpUrl.parse(AppConstants.BASE_URL)!!)
        fun create(httpUrl: HttpUrl): WebserviceApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebserviceApi::class.java)
        }
    }
}