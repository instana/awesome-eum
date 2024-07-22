package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.Payment
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentUseCase @Inject constructor(private val userHandlerRepository: UserHandlerRepository) {

    fun validatePayment(payment: Payment): Flow<EventState<ResponseGeneric?>> = flow {
        try {
            emit(EventState.Loading())
            val userResponse = userHandlerRepository.paymentValidate(payment)
            emit(EventState.Success(data = userResponse))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}