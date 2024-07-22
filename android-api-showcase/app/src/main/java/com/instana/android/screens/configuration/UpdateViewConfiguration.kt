package com.instana.android.screens.configuration

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.instana.android.Instana
import com.instana.android.core.SuspendReportingType
import com.instana.android.databinding.ViewUpdateConfigurationBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpdateViewConfiguration : BaseFragment<ViewUpdateConfigurationBinding>() {

    private lateinit var preference: MyPreferences

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize shared preferences
        preference = MyPreferences(requireContext().applicationContext)

        // Set the view name for Instana monitoring
        Instana.view = Constants.VIEW_UPDATE_CONFIG_SCREEN

        // Initialize the UI with the saved configuration data
        initUiWithData()
    }

    private fun initUiWithData() {
        with(binding) {
            // Retrieve saved configuration values from shared preferences and populate the UI
            instanaKey.setText(preference.getValue(Constants.InstanaKey, "").toString())
            reportingUrl.setText(preference.getValue(Constants.InstanaReportingUrl, "").toString())
            when (preference.getValue(
                Constants.SUSPENSION_TYPE,
                SuspendReportingType.NEVER.toString()
            ).toString()) {
                SuspendReportingType.LOW_BATTERY.toString() -> checkRadioButtonValue(binding.lowBattery.id)
                SuspendReportingType.LOW_BATTERY_OR_CELLULAR_CONNECTION.toString() -> checkRadioButtonValue(
                    binding.lowBatteryOrCellular.id
                )
                SuspendReportingType.NEVER.toString() -> checkRadioButtonValue(binding.never.id)
                SuspendReportingType.CELLULAR_CONNECTION.toString() -> checkRadioButtonValue(binding.cellularConnection.id)
            }
            collectionEnabledSwitch.isChecked  = preference.getValue(Constants.COLLECTION_ENABLED,true) as Boolean
            crashReportingSwitch.isChecked = preference.getValue(Constants.CRASH_REPORTING_ENABLED,true) as Boolean
            updateAndRestart.setOnClickListener {
                updateAndSave()
            }
        }
    }

    private fun updateAndSave(){
        with(binding){
            showLoader()
            // Save the updated configuration values to shared preferences
            preference.setValue(Constants.InstanaKey,instanaKey.text.toString())
            preference.setValue(Constants.InstanaReportingUrl,reportingUrl.text.toString())
            preference.setValue(Constants.SUSPENSION_TYPE,idToStringSuspensionType(radioGroupSuspensionType.checkedRadioButtonId))
            preference.setValue(Constants.COLLECTION_ENABLED,collectionEnabledSwitch.isChecked)
            preference.setValue(Constants.CRASH_REPORTING_ENABLED,crashReportingSwitch.isChecked)

// Delay the restart of the application by 2 seconds using coroutines
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000L)
                hideLoader()

                // Create an intent to launch the application's main activity
                val intent = activity?.packageName?.let {
                    activity?.packageManager?.getLaunchIntentForPackage(
                        it
                    )
                }

                // Add flag to clear the activity stack
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                // Start the main activity
                intent?.let {
                    startActivity(it)
                }

                // Finish the current activity
                activity?.finish()
            }

        }
    }

    private fun checkRadioButtonValue(id: Int) {
        binding.radioGroupSuspensionType.check(id)
    }

    private fun idToStringSuspensionType(id:Int):String{
        return when(id){
            binding.never.id -> SuspendReportingType.NEVER.toString()
            binding.lowBattery.id -> SuspendReportingType.LOW_BATTERY.toString()
            binding.lowBatteryOrCellular.id -> SuspendReportingType.LOW_BATTERY_OR_CELLULAR_CONNECTION.toString()
            binding.cellularConnection.id -> SuspendReportingType.CELLULAR_CONNECTION.toString()
            else -> SuspendReportingType.NEVER.toString()
        }
    }
}
