package com.ibm.instashop.common.utils.efffects

import android.content.Context
import android.content.pm.PackageManager
import com.ibm.instashop.common.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.getExactImageUrl():String{
    return if(this.contains("http")){
        this
    }else{
        "${Constants.BASE_URL.removeSuffix("v1/")}${this.removePrefix("/")}"
    }
}

fun Long.millisToDateTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return sdf.format(calendar.time)
}


fun getAppVersion(context: Context): String {
    return try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        "Unknown"
    }
}