package com.ibm.instashop.data.local

import com.ibm.instashop.business_unit.models.CartItems
import com.ibm.instashop.business_unit.models.ProductItem

object DBMock {
    var selectedItem:ProductItem? = null
    val cartItemsList:ArrayList<CartItems> = arrayListOf()
}