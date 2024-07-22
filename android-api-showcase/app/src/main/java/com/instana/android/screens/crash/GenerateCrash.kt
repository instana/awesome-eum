package com.instana.android.screens.crash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.instana.android.Instana
import com.instana.android.databinding.CrashGenerateBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.showInputDialog
import com.instana.android.utils.simulateANR

class GenerateCrash : BaseFragment<CrashGenerateBinding>(){

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the view name for Instana monitoring
        Instana.view = Constants.GENERATE_CRASH_SCREEN

        with(binding){
            // Set the click listeners for each crash generation button

            // Button to generate an ArithmeticException
            arithmeticException.setOnClickListener {
                // Generate an ArithmeticException by dividing 10 by 0
                var error = 10/0
            }

            // Button to generate a custom exception with user-defined message
            customThrowMessages.setOnClickListener {
                // Show an input dialog to get the crash message from the user
                showInputDialog("Generate Crash",requireContext()){
                    // Throw an exception with the user-provided message
                    throw Exception(it)
                }
            }

            // Button to generate a NullPointerException
            nullPointer.setOnClickListener {
                // Create a nullable string and access its length, triggering a NullPointerException
                val x:String? = null
                x!!.length
            }

            // Button to simulate an Application Not Responding (ANR) error
            anrError.setOnClickListener {
                // Simulate an ANR error
                simulateANR()
            }
        }
    }
}
