package com.instana.android.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun callToCustomUrl(@Url url: String): Any

    @GET("entries")
    suspend fun callToDemoUrl(): Any
}