package com.ibm.instashop.common

data class DataState(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val errorMessage: String = ""
)
