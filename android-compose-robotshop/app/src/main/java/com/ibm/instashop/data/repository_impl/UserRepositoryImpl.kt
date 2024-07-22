package com.ibm.instashop.data.repository_impl

import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.models.User
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.common.Constants
import com.ibm.instashop.data.network.ApiService
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    @Named("ApiService1") private val apiService1: ApiService,
    @Named("ApiService2") private val apiService2: ApiService,
) : UserHandlerRepository {
    override suspend fun validateUser(user:User): ResponseGeneric? {
        return getApiService().validateUser(user)
    }

    override suspend fun createUser(user:User): ResponseGeneric? {
        return getApiService().createUser(user)
    }

    override suspend fun notifications(): ArrayList<Notification>? {
        return getApiService().getNotifications()
    }

    override suspend fun getDiscount(discountCoupon: DiscountCoupon): DiscountResponse? {
        return getApiService().getDiscount(discountCoupon)
    }

    override suspend fun paymentValidate(payment: Payment): ResponseGeneric? {
        return getApiService().paymentValidate(payment)
    }

    private fun getApiService():ApiService{
        return if (Constants.CurrentVersionInUse == Constants.VERSION_NUMBER_V2) apiService2 else apiService1
    }
}