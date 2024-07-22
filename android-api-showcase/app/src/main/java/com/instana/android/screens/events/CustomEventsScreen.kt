package com.instana.android.screens.events

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.instana.android.CustomEvent
import com.instana.android.Instana
import com.instana.android.databinding.CustomEventScreenBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.showInfoDialog
import com.instana.android.utils.toLong

class CustomEventsScreen:BaseFragment<CustomEventScreenBinding>() {

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the view name for Instana monitoring
        Instana.view = Constants.CUSTOM_EVENT_SCREEN

        binding.createEventBtn.setOnClickListener {
            createCustomEvent()
        }
    }

    @SuppressLint("Range")
    private fun createCustomEvent(){
        with(binding){
            // Create a CustomEvent object using the entered event name and additional details
            val myEvent = CustomEvent(customEventName.text.toString()).apply {
                duration = binding.duration.text?.toLong()
                meta = mapOf(metaKey.text.toString() to metaValue.text.toString())
                backendTracingID = backendId.text.toString()
            }

            // Report the custom event to Instana
            Instana.reportEvent(myEvent)

            // Show a dialog to indicate that the event was created and sent
            showInfoDialog("Event Created","The Event was created and beacons were sent",requireContext())
        }
    }
}
