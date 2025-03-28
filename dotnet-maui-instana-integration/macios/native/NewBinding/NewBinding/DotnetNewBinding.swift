//
//  DotnetNewBinding.swift
//  NewBinding
//
//  Created by .NET MAUI team on 6/18/24.
//

import Foundation
import InstanaAgent

@objc(DotnetNewBinding)
public class DotnetNewBinding : NSObject
{

    @objc
    public static func getString(myString: String) -> String {
        return myString  + " from swift!"
    }
    
    @objc
    public static func setup(key: String,url: String) {
        Instana.setupInternal(key: key, reportingURL: URL(string: url)!, options: InstanaSetupOptions(), hybridOptions: HybridAgentOptions(id: "maui", version: "0.0.1"))
    }
    
    @objc
    public static func setView(view: String) {
        Instana.setView(name: view)
    }

    @objc
    public static func setUserId(userId: String) {
        Instana.setUser(id: userId)
    }
    
    @objc
    public static func setUserName(userName: String) {
        Instana.setUser(name: userName)
    }
    
    @objc
    public static func setUserEmail(userEmail: String) {
        Instana.setUser(email: userEmail)
    }
    
    @objc
    public static func setScreenNameAutoCapture(autoCapture: Bool) {
        //Impl
    }
    
    @objc
    public static func setCrashReporting(crashReporting: Bool) {
        //Impl
    }
    
    @objc
    public static func setCustomEvent(eventName: String) {
        Instana.reportEvent(name: eventName)
    }
}
