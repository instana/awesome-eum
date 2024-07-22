package com.ibm.instashop.business_unit.usecases

import com.ibm.instashop.business_unit.models.Notification
import com.ibm.instashop.business_unit.repository.UserHandlerRepository
import com.ibm.instashop.common.EventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationsUseCase @Inject constructor(
    private val repository: UserHandlerRepository
) {
    operator fun invoke(): Flow<EventState<ArrayList<Notification>>> = flow {
        try {
            emit(EventState.Loading())
            val notifications = repository.notifications()
            emit(EventState.Success(data = notifications))
        } catch (e: Exception) {
            emit(EventState.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}