package com.ibm.instashop.business_unit.models

data class OrderSuccess (
   val totalAmount: Double ,
   val orderId: Int ,
   val category:String
)