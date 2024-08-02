package com.ibm.instashop.di

import android.app.Application
import com.ibm.instashop.common.Constants
import com.ibm.instashop.common.Constants.CurrentVersionInUse
import com.ibm.instashop.common.Constants.INSTANA_KEY
import com.ibm.instashop.common.Constants.INSTANA_URL
import com.ibm.instashop.data.local.DataManager
import com.instana.android.Instana
import com.instana.android.core.InstanaConfig
import com.instana.android.core.SuspendReportingType
import com.instana.android.instrumentation.HTTPCaptureConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){

    @Inject
    lateinit var dataManager: DataManager
    override fun onCreate() {
        super.onCreate()
        Thread.sleep(300)
        if(dataManager.getString(INSTANA_KEY).isNotEmpty()&&dataManager.getString(INSTANA_URL).isNotEmpty()){
           setupInstana(dataManager.getString(INSTANA_KEY),dataManager.getString(INSTANA_URL))
        }
        if(dataManager.getString(Constants.CURRENT_API_VERSION).isNotEmpty()){
            CurrentVersionInUse = dataManager.getString(Constants.CURRENT_API_VERSION)
        }
    }

    private fun setupInstana(key:String, url:String){
        Instana.setup(
            this,
            InstanaConfig(
                reportingURL = url,
                key = key,
                enableCrashReporting = true,
                autoCaptureScreenNames = true
            )
        )
    }
}