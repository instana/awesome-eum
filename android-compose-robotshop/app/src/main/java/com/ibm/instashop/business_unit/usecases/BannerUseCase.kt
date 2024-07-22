package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.Banner
import com.ibm.instashop.business_unit.repository.ProductHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BannerUseCase @Inject constructor(
    private val repository: ProductHandlerRepository
) {
    operator fun invoke(): Flow<EventState<ArrayList<Banner>>> = flow {
        try {
            emit(EventState.Loading())
            val bannerItems = repository.getBannerItems()
            emit(EventState.Success(data = bannerItems))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}