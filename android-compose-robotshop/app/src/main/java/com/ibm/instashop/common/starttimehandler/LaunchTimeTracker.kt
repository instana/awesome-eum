package com.ibm.instashop.common.starttimehandler

import com.instana.android.CustomEvent
import com.instana.android.Instana


object LaunchTimeTracker{

    private var initialTimeInNanos: Long = 0
    var launchTimeInNanos: Long = 0
    private var doneTracking = false
    fun startTimer(){
        this.initialTimeInNanos = System.nanoTime()
    }

    fun stopTimer():Boolean{
        if(doneTracking){
            return false
        }
        launchTimeInNanos = System.nanoTime() - this.initialTimeInNanos;
        Instana.reportEvent(CustomEvent("APP_START_TIMINGS").apply {
            meta = mapOf(
                "APP_START_LOAD_TIME_DURATION" to (launchTimeInNanos/1_000_000).toString()+"ms",
            )
        })
        doneTracking = true
        return true
    }
}