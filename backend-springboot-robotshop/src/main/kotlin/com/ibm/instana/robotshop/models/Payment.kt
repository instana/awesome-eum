package com.ibm.instana.robotshop.models

data class Payment(
    val cardNumber:String,
    val cvv:String,
    val expiryYear:String
)
