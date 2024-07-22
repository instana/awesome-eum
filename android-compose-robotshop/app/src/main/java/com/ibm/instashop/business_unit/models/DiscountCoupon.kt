package com.ibm.instashop.business_unit.models

data class DiscountCoupon(
    val couponId:String,
    val cartValue:Double,
    val userId:String,
    val percentage: Double
)
