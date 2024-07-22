package com.ibm.instashop.di

import android.content.Context
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.data.local.DataManager
import com.ibm.instashop.data.network.ApiService
import com.ibm.instashop.data.repository_impl.ProductRepositoryImp
import com.ibm.instashop.data.repository_impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideProductRepository(
        @Named("ApiService1") apiService1: ApiService,
        @Named("ApiService2") apiService2: ApiService
    ): ProductHandlerRepository {
        return ProductRepositoryImp(apiService1, apiService2)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        @Named("ApiService1") apiService1: ApiService,
        @Named("ApiService2") apiService2: ApiService
    ): UserHandlerRepository {
        return UserRepositoryImpl(apiService1, apiService2)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDataManager(context: Context): DataManager {
        return DataManager(context)
    }

}