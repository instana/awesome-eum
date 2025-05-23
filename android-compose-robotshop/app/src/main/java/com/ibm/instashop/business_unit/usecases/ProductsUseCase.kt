package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.ProductItem
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val repository: ProductHandlerRepository
) {
    operator fun invoke(): Flow<EventState<ArrayList<ProductItem>>> = flow {
        try {
            emit(EventState.Loading())
            val products = repository.getAllProducts()
            emit(EventState.Success(data = products))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}