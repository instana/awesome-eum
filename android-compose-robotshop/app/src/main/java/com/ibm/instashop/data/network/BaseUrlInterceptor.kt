package com.ibm.instashop.data.network

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseUrlInterceptor : Interceptor {
    @Volatile
    private var host: HttpUrl? = null

    fun setBaseUrl(baseUrl: String) {
        this.host = baseUrl.toHttpUrlOrNull()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        host?.let { baseHttpUrl ->
            val oldUrl = request.url

            // Ensure no extra slashes are added
            val newPath = baseHttpUrl.encodedPath.trimEnd('/') + "/" + oldUrl.encodedPath.removePrefix("/v1").trimStart('/')

            val newUrl = oldUrl.newBuilder()
                .scheme(baseHttpUrl.scheme)
                .host(baseHttpUrl.host)
                .port(baseHttpUrl.port)
                .encodedPath(newPath)
                .build()

            request = request.newBuilder()
                .url(newUrl)
                .build()
        }

        return chain.proceed(request)
    }

}
