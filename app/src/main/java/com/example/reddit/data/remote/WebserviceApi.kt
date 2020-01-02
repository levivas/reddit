package com.example.reddit.data.remote

import com.example.reddit.data.model.DataPost
import retrofit2.Call
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
}