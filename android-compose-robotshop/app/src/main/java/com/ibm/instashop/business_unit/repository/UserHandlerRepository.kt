package com.ibm.instashop.business_unit.repository

import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.models.User


interface UserHandlerRepository {
    suspend fun validateUser(user: User): ResponseGeneric? = null
    suspend fun createUser(user:User): ResponseGeneric? = null
    suspend fun notifications(): ArrayList<Notification>? = null
    suspend fun getDiscount(discountCoupon: DiscountCoupon):DiscountResponse? =null
    suspend fun paymentValidate(payment: Payment):ResponseGeneric? = null
}