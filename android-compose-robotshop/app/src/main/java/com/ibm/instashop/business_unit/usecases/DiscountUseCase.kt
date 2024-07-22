package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.DiscountCoupon
import com.ibm.instashop.business_unit.models.DiscountResponse
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiscountUseCase @Inject constructor(private val repository: UserHandlerRepository) {

    fun getDiscount(discountCoupon: DiscountCoupon): Flow<EventState<DiscountResponse>> = flow {
        emit(EventState.Loading())
        val discountResponse = repository.getDiscount(discountCoupon)
        emit(EventState.Success(data = discountResponse))
    }
}