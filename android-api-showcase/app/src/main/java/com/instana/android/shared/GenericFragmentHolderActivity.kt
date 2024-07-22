package com.instana.android.shared

import android.os.Bundle
import com.instana.android.databinding.GenericFragmentHolderBinding
import com.instana.android.screens.addviews.FirstNamedScreen
import com.instana.android.screens.addviews.SecondNamedScreen
import com.instana.android.screens.configuration.UpdateViewConfiguration
import com.instana.android.screens.crash.GenerateCrash
import com.instana.android.screens.events.CustomEventsScreen
import com.instana.android.screens.logs.CreateTestLogs
import com.instana.android.screens.newtwork.NetworkCallScreen
import com.instana.android.screens.profile.ProfileFragment
import com.instana.android.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity that holds a generic fragment based on the screen option received.
 * Handles the creation of the view binding and initializes the UI based on the received screen option.
 */
@AndroidEntryPoint
class GenericFragmentHolderActivity: BaseActivity<GenericFragmentHolderBinding>() {

    /**
     * Initializes the activity, inflates the view binding, and initializes the UI based on the screen option received.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createBinding<GenericFragmentHolderBinding> { inflater -> GenericFragmentHolderBinding.inflate(inflater) }
        val screenName = intent.getStringExtra(Constants.SCREEN_ID) ?: ""
        initUiWithScreenReceived(screenName)
    }

    /**
     * Initializes the UI based on the received screen option.
     *
     * @param optionSelected The selected screen option.
     */
    private fun initUiWithScreenReceived(optionSelected:String){
        when(optionSelected){
            Constants.CREATE_PROFILE -> {
                replaceFragment(binding.fragmentContainer.id, ProfileFragment(),false)
            }
            Constants.GENERATE_CRASH -> {
                replaceFragment(binding.fragmentContainer.id, GenerateCrash(),false)
            }
            Constants.MULTI_SCREEN_VIEW -> {
                replaceFragment(binding.fragmentContainer.id, FirstNamedScreen.newInstance {
                    addFragment(binding.fragmentContainer.id, SecondNamedScreen(),true)
                },false)
            }
            Constants.NETWORK_OPERATION -> {
                replaceFragment(binding.fragmentContainer.id, NetworkCallScreen(),false)
            }
            Constants.CUSTOM_EVENTS -> {
                replaceFragment(binding.fragmentContainer.id, CustomEventsScreen(),false)
            }
            Constants.VIEW_UPDATE_CONFIG -> {
                replaceFragment(binding.fragmentContainer.id, UpdateViewConfiguration(),false)
            }
            Constants.CUSTOM_LOGS_SCREEN -> {
                replaceFragment(binding.fragmentContainer.id, CreateTestLogs(),false)
            }
        }
    }
}
