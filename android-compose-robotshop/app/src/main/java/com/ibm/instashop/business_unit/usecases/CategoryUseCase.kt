package com.ibm.instashop.business_unit.usecases

import android.util.Log
import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: ProductHandlerRepository
) {
    operator fun invoke(category:String): Flow<EventState<ArrayList<ProductItem>>> = flow {
        try {
            emit(EventState.Loading())
            val products = repository.fetchProductByCategory(category)
            emit(EventState.Success(data = products))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}