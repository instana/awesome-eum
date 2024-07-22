package com.instana.android.network.retrofit

import javax.inject.Inject

class RetrofitNetworkRepository @Inject constructor(private val apiService: ApiService){
    suspend fun callToCustomUrl(customUrl:String):Any{
        return apiService.callToCustomUrl(customUrl)
    }

    suspend fun callToDemoUrl():Any{
        return apiService.callToDemoUrl()
    }
}
