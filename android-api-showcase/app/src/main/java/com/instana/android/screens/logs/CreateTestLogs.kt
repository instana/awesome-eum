package com.instana.android.screens.logs

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.instana.android.Instana
import com.instana.android.Logger
import com.instana.android.databinding.TestLogsScreenBinding
import com.instana.android.shared.BaseFragment
import com.instana.android.utils.Constants
import com.instana.android.utils.showInfoDialog

class CreateTestLogs:BaseFragment<TestLogsScreenBinding>(),Logger {

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the view name for Instana monitoring
        Instana.view = Constants.CUSTOM_LOGS_SCREEN

        binding.levelRadioGroup.check(binding.info.id)

        binding.crateLogBtn.setOnClickListener {
            createLog()
        }
    }

    private fun createLog(){
        with(binding){
            // Create a log based on the selected log level, tag, and message

            // Use Log.d() for DEBUG level logs
            Log.d(logTag.text.toString(),logMessage.text.toString(),null)

            // For other log levels, use the custom log() function implemented in the Logger interface

            // Use Log.ERROR level and include the error details if the log level is ERROR
            when(getLogLevelFromRadioBtn()){
                Log.ERROR -> log(getLogLevelFromRadioBtn(),logTag.text.toString(),logMessage.text.toString(),Throwable(logError.text.toString()))
                else -> log(getLogLevelFromRadioBtn(),logTag.text.toString(),logMessage.text.toString(),null)
            }

            // Show a dialog to indicate that the log was created and sent
            showInfoDialog("Log Created","The Log was created and beacons were sent",requireContext())
        }
    }

    private fun getLogLevelFromRadioBtn():Int{
        return when(binding.levelRadioGroup.checkedRadioButtonId){
            binding.info.id -> Log.INFO
            binding.error.id ->Log.ERROR
            binding.warn.id ->Log.WARN
            binding.debug.id ->Log.DEBUG
            binding.verbose.id ->Log.VERBOSE
            else -> Log.INFO //Default info
        }
    }

    override fun log(level: Int, tag: String, message: String, error: Throwable?) {
        // Log the message based on the specified log level and include error details if available

        when(level){
            Log.DEBUG -> Log.d(tag,message,null)
            Log.WARN -> Log.w(tag,message,null)
            Log.INFO -> Log.i(tag,message,null)
            Log.VERBOSE -> Log.v(tag,message,null)
            Log.ERROR -> Log.d(tag,message,error)
            else -> {} // Do nothing for other log levels
        }
    }
}
