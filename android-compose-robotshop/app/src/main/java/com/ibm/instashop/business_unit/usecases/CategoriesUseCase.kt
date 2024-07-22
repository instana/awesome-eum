package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repository: ProductHandlerRepository
) {
    operator fun invoke(): Flow<EventState<List<String>>> = flow {
        try {
            emit(EventState.Loading())
            val category = repository.categoriesList()
            emit(EventState.Success(data = category))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}