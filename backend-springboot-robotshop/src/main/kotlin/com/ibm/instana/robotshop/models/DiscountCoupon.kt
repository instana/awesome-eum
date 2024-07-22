package com.ibm.instana.robotshop.models

import jdk.jfr.Percentage

data class DiscountCoupon(
    val couponId:String,
    val cartValue:Double,
    val userId:String,
    val percentage: Double
)
