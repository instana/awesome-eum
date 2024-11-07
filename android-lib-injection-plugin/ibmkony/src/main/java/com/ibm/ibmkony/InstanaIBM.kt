package com.ibm.ibmkony

import android.app.Application
import com.instana.android.Instana
import com.instana.android.core.InstanaConfig

object InstanaIBM {
    fun setup(app:Application){
        Instana.setup(app,InstanaConfig("KEY","URL"))
    }
    fun setViewName(view:String){
        Instana.view = view
    }
    fun setUsername(userName: String){
        Instana.userName = userName
    }
    fun setUserId(userId: String){
        Instana.userId = userId
    }
    fun setUserEmail(email: String){
        Instana.userEmail = email
    }
}