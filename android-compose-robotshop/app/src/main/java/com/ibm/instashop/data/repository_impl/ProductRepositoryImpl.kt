package com.ibm.instashop.data.repository_impl

import android.util.Log
import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.Constants
import com.ibm.instashop.common.Constants.CurrentVersionInUse
import com.ibm.instashop.common.Constants.VERSION_NUMBER_V2
import com.ibm.instashop.data.network.ApiService
import javax.inject.Inject
import javax.inject.Named

class ProductRepositoryImp @Inject constructor(
    @Named("ApiService1") private val apiService1: ApiService,
    @Named("ApiService2") private val apiService2: ApiService,
) : ProductHandlerRepository {
    override suspend fun getAllProducts(): ArrayList<ProductItem>? {
        return getApiService().allProducts()
    }

    override suspend fun fetchProductById(id: Int): ProductItem? {
        return getApiService().fetchProductById(id)
    }

    override suspend fun fetchProductByCategory(string: String): ArrayList<ProductItem>? {
        return getApiService().fetchProductByCategory(string)
    }

    override suspend fun categoriesList(): ArrayList<String>? {
        return getApiService().categoriesList()
    }

    override suspend fun getBannerItems(): ArrayList<Banner>? {
        return getApiService().getBannerItems()
    }

    private fun getApiService():ApiService{
        return if (CurrentVersionInUse == VERSION_NUMBER_V2) apiService2 else apiService1
    }


}