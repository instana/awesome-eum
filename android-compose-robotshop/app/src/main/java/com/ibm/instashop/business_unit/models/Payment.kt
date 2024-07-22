package com.ibm.instashop.business_unit.models

data class Payment(
    val cardNumber:String?,
    val cvv:String?,
    val expiryYear:String?
)
