package com.ibm.instashop.data.network

import com.ibm.instashop.common.Constants.BASE_URL
import com.ibm.instashop.common.Constants.BASE_URL_V2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrlInterceptor(): BaseUrlInterceptor {
        return BaseUrlInterceptor()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(baseUrlInterceptor: BaseUrlInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    @RetrofitInstance1
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RetrofitInstance2
    fun provideRetrofit2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_V2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("ApiService1")
    fun provideApiService(@RetrofitInstance1 retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("ApiService2")
    fun provideApiService2(@RetrofitInstance2 retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitInstance1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitInstance2