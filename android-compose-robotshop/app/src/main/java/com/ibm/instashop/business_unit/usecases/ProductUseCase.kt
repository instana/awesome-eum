package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ProductHandlerRepository
) {
    operator fun invoke(id:Int): Flow<EventState<ProductItem>> = flow {
        try {
            emit(EventState.Loading())
            val products = repository.fetchProductById(id)
            emit(EventState.Success(data = products))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}