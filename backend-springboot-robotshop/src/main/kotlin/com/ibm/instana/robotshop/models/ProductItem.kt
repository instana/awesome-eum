package com.ibm.instana.robotshop.models

data class ProductItem(
        val category: String,
        val description: String,
        val id: Int,
        val image: String,
        val price: Double,
        val rating: Rating,
        val title: String
)