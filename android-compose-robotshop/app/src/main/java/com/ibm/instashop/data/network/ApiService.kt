package com.ibm.instashop.data.network

import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("product/all")
    suspend fun allProducts(): ArrayList<ProductItem>

    @GET("product/{id}")
    suspend fun fetchProductById(@Path("id") id: Int): ProductItem

    @GET("product/categories")
    suspend fun categoriesList(): ArrayList<String>

    @GET("product/categories/{category}")
    suspend fun fetchProductByCategory(@Path("category") category: String): ArrayList<ProductItem>

    @GET("banner/all")
    suspend fun getBannerItems(): ArrayList<Banner>

    @POST("user/validate")
    suspend fun validateUser(@Body requestBody:User): ResponseGeneric?

    @POST("user/create")
    suspend fun createUser(@Body requestBody:User): ResponseGeneric?

    @GET("notifications/all")
    suspend fun getNotifications():ArrayList<Notification>?

    @POST("discount/coupon")
    suspend fun getDiscount(@Body requestBody: DiscountCoupon):DiscountResponse?

    @POST("payment/validate")
    suspend fun paymentValidate(@Body payment: Payment):ResponseGeneric?

}