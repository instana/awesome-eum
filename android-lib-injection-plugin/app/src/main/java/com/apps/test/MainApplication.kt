package com.apps.test

import android.app.Application
import com.ibm.ibmkony.InstanaIBM

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //This way you can initialise Instana from your custom library.
        InstanaIBM.setup(
            this
        )
        //Using APIs from your library to call instana native methods.
        InstanaIBM.setUserId("User ID Value")
        InstanaIBM.setUserEmail("User Email Value")
        InstanaIBM.setUsername("User Name Value")
        InstanaIBM.setViewName("TEST_VIEW")
    }

}
