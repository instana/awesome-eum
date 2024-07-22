package com.ibm.instashop.screens.product_details

import androidx.lifecycle.ViewModel
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.models.Rating
import com.ibm.instashop.business_unit.usecases.CartUseCase
import com.ibm.instashop.data.local.DBMock
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    fun getSelectedItem():ProductItem{
        return DBMock.selectedItem?: ProductItem(
            category = "odio",
            description = "rhoncus",
            id = 5767,
            image = "postulant",
            price = 6.7,
            rating = Rating(count = 8675, rate = 8.9),
            title = "pro"
        );
    }

    fun updateCart(productItem: ProductItem,count:Int){
        cartUseCase.updateCart(productItem,count)
    }

    fun selectedItemCount():Int{
        val selectedItemCart =  DBMock.cartItemsList.find { it.item.id == DBMock.selectedItem?.id }
        return selectedItemCart?.count ?: 0
    }

}