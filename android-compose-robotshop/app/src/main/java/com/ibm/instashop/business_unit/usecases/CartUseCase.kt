package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.CartItems
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.data.local.DBMock
import javax.inject.Inject

class CartUseCase @Inject constructor(
) {
    fun updateCart(productItem: ProductItem,count:Int){
        val existingCartItem = DBMock.cartItemsList.find { it.item.id == productItem.id }
        if(count==0 && existingCartItem!=null){
            DBMock.cartItemsList.remove(existingCartItem)
        }else{
            if(existingCartItem != null){
                val indexValue = DBMock.cartItemsList.indexOf(existingCartItem)
                DBMock.cartItemsList[indexValue] = existingCartItem.copy(count= count)
            }else{
                val newCartItem = CartItems(count, productItem)
                DBMock.cartItemsList.add(newCartItem)
            }
        }
    }

}