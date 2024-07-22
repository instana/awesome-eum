package com.instana.android.utils

/**
 * A sealed class representing the state of data.
 */
sealed class DataState<out T> {
    /**
     * Represents an empty state with no data.
     */
    object Empty : DataState<Nothing>()

    /**
     * Represents a loading state.
     */
    object Loading : DataState<Boolean>()

    /**
     * Represents a success state with the associated data.
     *
     * @param data The data of type [T].
     */
    data class Success<out T>(val data: T) : DataState<T>()

    /**
     * Represents an error state with the associated error message.
     *
     * @param errorMessage The error message.
     */
    data class Error(val errorMessage: String) : DataState<Nothing>()
}
