package com.example.newbinding;

import android.app.Activity;
import android.util.Log;

import com.instana.android.CustomEvent;
import com.instana.android.Instana;
import com.instana.android.core.HybridAgentOptions;
import com.instana.android.core.InstanaConfig;

public class DotnetNewBinding
{
    public static String getString(String myString)
    {
        return myString + " from java!123";
    }

    public static Boolean setup(Activity activity, String key, String url){
        InstanaConfig config = new InstanaConfig(key,url);
        config.setEnableCrashReporting(true);
        HybridAgentOptions hybridAgentOptions= new HybridAgentOptions("maui","0.0.1");
        if(activity.getApplication() != null){
            Instana.setupInternal(activity.getApplication(),config,hybridAgentOptions);
        }else {
            Log.e("Instana- ","No Application Context available");
        }
        return true;
    }

    public static void setView(String view){
        Instana.setView(view);
    }

    public static void setUserId(String userId){
        Instana.setUserId(userId);
    }

    public static void setUserName(String userName){
        Instana.setUserName(userName);
    }

    public static void setUserEmail(String userEmail){
        Instana.setUserEmail(userEmail);
    }

    public static void setScreenNameAutoCapture(Boolean autoCapture){
        Instana.setAutoCaptureScreenNameEnabled(autoCapture);
    }

    public static void setCrashReporting(Boolean crashReporting){
        Instana.setCrashReportingEnabled(crashReporting);
    }

    public static void setCustomEvent(String eventName){
        CustomEvent event = new  CustomEvent(
                eventName
        );
        Instana.reportEvent(event);
    }
}
