package com.instana.android.screens.newtwork

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instana.android.network.http.makeHttpGetRequest
import com.instana.android.network.retrofit.RetrofitNetworkRepository
import com.instana.android.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val retrofitNetworkRepository: RetrofitNetworkRepository): ViewModel() {

    // StateFlow to emit the data state to the UI
    private val _dataStateFlow = MutableStateFlow<DataState<Any>>(DataState.Empty)
    val dataStateFlow: StateFlow<DataState<Any>> = _dataStateFlow

    /**
     * Makes a network API call to the demo URL using Retrofit.
     * Emits the data state to the UI.
     */
    fun getRetrofitDemoUrlData() {
        viewModelScope.launch {
            try {
                _dataStateFlow.value = DataState.Loading
                val data = retrofitNetworkRepository.callToDemoUrl()
                _dataStateFlow.value = DataState.Success(data)
            } catch (e: Exception) {
                _dataStateFlow.value = DataState.Error("Failed to fetch demo URL: ${e.message}")
            }
        }
    }

    /**
     * Makes a network API call to a custom URL using Retrofit.
     * Emits the data state to the UI.
     *
     * @param url The custom URL for the API call.
     */
    fun getRetrofitCustomUrlData(url: String) {
        viewModelScope.launch {
            try {
                _dataStateFlow.value = DataState.Loading
                val data = retrofitNetworkRepository.callToCustomUrl(url)
                _dataStateFlow.value = DataState.Success(data)
            } catch (e: Exception) {
                _dataStateFlow.value = DataState.Error("Failed to fetch custom URL: ${e.message}")
            }
        }
    }

    /**
     * Makes a network API call using HttpURLConnection.
     * Emits the data state to the UI.
     *
     * @param url The URL for the API call.
     */
    fun performNetworkRequestForHttpURLConnection(url:String) {
        _dataStateFlow.value = DataState.Loading
        makeHttpGetRequest(url,
            onSuccess = { response ->
                Log.i("API_RESPONSE_HttpURLConnection",response)
                _dataStateFlow.value = DataState.Success(response)
            },
            onError = { exception ->
                Log.i("API_RESPONSE_HttpURLConnection_ERROR",exception.message.toString())
                _dataStateFlow.value = DataState.Error("Failed to fetch custom URL: ${exception.message}")
            }
        )
    }
}
