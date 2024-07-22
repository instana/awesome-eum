package com.instana.android.screens.newtwork

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.instana.android.Instana
import com.instana.android.databinding.NetworkCallBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.DataState
import com.instana.android.utils.showInfoDialog
import com.instana.android.utils.showInputDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NetworkCallScreen : BaseFragment<NetworkCallBinding>() {
    private val viewModel: NetworkViewModel by viewModels()

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the view name for Instana monitoring
        Instana.view = Constants.NETWORK_OPERATION_SCREEN

        with(binding){
            radioGroup.check(retrofit.id)

            createNetworkCallBtn.setOnClickListener {
                networkClientBasedApiCall("https://api.publicapis.org/entries",false)
            }

            createNetworkCallInfo.setOnClickListener {
                showInfoDialog("Network Call","This will make a GET request to public API: \n https://api.publicapis.org/entries \n based on the NetworkClient you have opted for.",requireContext())
            }

            createCustomNetworkCallBtn.setOnClickListener {
                showInputDialog("Make Call",requireContext()){
                    networkClientBasedApiCall(it,true)
                }
            }

            createCustomCallInfo.setOnClickListener {
                showInfoDialog("Network Call","This will make a GET request to any URL you provide based on the NetworkClient you have opted for.",requireContext())
            }
        }

        collectFlows()
    }

    /**
     * Makes a network API call based on the selected network client.
     *
     * @param url The URL for the API call.
     * @param isCustomUrl A flag indicating whether a custom URL is provided.
     */
    private fun networkClientBasedApiCall(url: String, isCustomUrl: Boolean) {
        with(binding){
            when(radioGroup.checkedRadioButtonId){
                retrofit.id -> {
                    if (isCustomUrl) {
                        viewModel.getRetrofitCustomUrlData(url)
                    } else {
                        viewModel.getRetrofitDemoUrlData()
                    }
                }
                okHttp.id -> viewModel.performNetworkRequestForHttpURLConnection(url)
                httpUrl.id -> {
                    if (isCustomUrl) {
                        viewModel.getRetrofitCustomUrlData(url)
                    } else {
                        viewModel.getRetrofitDemoUrlData()
                    }
                }
            }
        }
    }

    /**
     * Observes the data state flow and updates the UI accordingly.
     */
    private fun collectFlows() {
        lifecycleScope.launch {
            viewModel.dataStateFlow.collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        showLoader()
                    }
                    is DataState.Success<Any> -> {
                        hideLoader()
                        dataState.data
                        // Update UI with user data
                    }
                    is DataState.Error -> {
                        hideLoader()
                        dataState.errorMessage
                        // Handle error and display error message
                    }
                    else -> {
                        // Empty
                    }
                }
            }
        }
    }
}
