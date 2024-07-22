package com.instana.android

import android.app.Application
import com.instana.android.core.InstanaConfig
import com.instana.android.core.SuspendReportingType
import com.instana.android.utils.Constants
import com.instana.android.utils.MyPreferences
import com.instana.android.utils.getSuspensionType
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom Application class for initializing Instana SDK and setting up configuration.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Retrieve IBM Instana configuration values from shared preferences
        val preference = MyPreferences(this)
        val instanaKey = preference.getValue(Constants.InstanaKey, "")
        val reportingUrl = preference.getValue(Constants.InstanaReportingUrl, "")
        val collectionEnabled = preference.getValue(Constants.COLLECTION_ENABLED, true) as Boolean
        val enableCrashReporting = preference.getValue(Constants.CRASH_REPORTING_ENABLED, true) as Boolean
        val suspendReporting =
            preference.getValue(Constants.SUSPENSION_TYPE, SuspendReportingType.LOW_BATTERY.toString())
                .toString().getSuspensionType()

        // Initialize IBM Instana SDK with the retrieved configuration values
        if (instanaKey.toString().isNotEmpty() && instanaKey.toString().isNotBlank()) {
            Instana.setup(
                this,
                InstanaConfig(
                    reportingURL = reportingUrl.toString(),
                    key = instanaKey.toString(),
                    enableCrashReporting = enableCrashReporting,
                    suspendReporting = suspendReporting,
                    collectionEnabled = collectionEnabled,
                )
            )
        }
    }
}
