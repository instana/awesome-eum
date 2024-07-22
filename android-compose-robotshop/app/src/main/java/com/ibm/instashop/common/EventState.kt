package com.ibm.instashop.common

sealed class EventState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>() : EventState<T>()
    class Success<T>(data: T? = null) : EventState<T>(data = data)
    class Error<T>(message: String? = null) : EventState<T>(message = message)
}