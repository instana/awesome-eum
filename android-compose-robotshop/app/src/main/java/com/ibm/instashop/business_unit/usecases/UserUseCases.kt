package com.ibm.instashop.business_unit.usecases

import android.util.Log
import com.ibm.instashop.business_unit.models.ResponseGeneric
import com.ibm.instashop.business_unit.models.User
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCases @Inject constructor(private val repository: UserHandlerRepository) {

    fun validateUser(user: User): Flow<EventState<ResponseGeneric?>> = flow {
        try {
            emit(EventState.Loading())
            val userResponse = repository.validateUser(user)
            emit(EventState.Success(data = userResponse))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    fun createUser(user: User): Flow<EventState<ResponseGeneric?>> = flow {
        try {
            emit(EventState.Loading())
            val userResponse = repository.createUser(user)
            emit(EventState.Success(data = userResponse))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}