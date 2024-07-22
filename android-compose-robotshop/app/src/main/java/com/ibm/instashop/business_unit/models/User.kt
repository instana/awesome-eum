package com.ibm.instashop.business_unit.models

data class User(
    val name: String?,
    val email:String? = "",
    val password: String?,
)
