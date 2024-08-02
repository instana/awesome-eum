package com.ibm.instashop.common.starttimehandler

import android.os.SystemClock
import android.util.Log
import com.instana.android.CustomEvent
import com.instana.android.Instana


object LaunchTimeTracker{

    private var initialTimeInNanos: Long = 0
    var launchTimeInNanos: Long = 0
    private var doneTracking = false
    fun startTimer(){
        this.initialTimeInNanos = SystemClock.elapsedRealtime()
    }

    fun stopTimer():Boolean{
        if(doneTracking){
            return false
        }
        launchTimeInNanos = SystemClock.elapsedRealtime() - this.initialTimeInNanos;
        Instana.reportEvent(CustomEvent("APP_START_TIMINGS").apply {
            duration = launchTimeInNanos
            meta = mapOf(
                "APP_START_LOAD_TIME_DURATION" to (launchTimeInNanos).toString()+"ms",
            )
        })
        Log.i("Displayed com",(launchTimeInNanos).toString()+"ms")
        doneTracking = true
        return true
    }
}