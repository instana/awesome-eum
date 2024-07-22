package com.ibm.instashop.business_unit.repository

import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.models.ProductItem

interface ProductHandlerRepository {
    suspend fun getAllProducts(): ArrayList<ProductItem>? = null
    suspend fun fetchProductById(id: Int): ProductItem? = null
    suspend fun categoriesList(): ArrayList<String>? = null
    suspend fun fetchProductByCategory(string: String): ArrayList<ProductItem>? = null
    suspend fun getBannerItems(): ArrayList<Banner>? = null
}