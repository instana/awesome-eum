package com.ibm.instashop.business_unit.models

data class Notification(
    val title:String,
    val subTitle:String,
    val dateTime:String,
    val complete:Boolean
)
