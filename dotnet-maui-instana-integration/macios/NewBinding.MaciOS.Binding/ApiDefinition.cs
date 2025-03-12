using Foundation;

namespace NewBindingMaciOS
{
    // @interface DotnetNewBinding : NSObject
    [BaseType(typeof(NSObject))]
    interface DotnetNewBinding
    {
        // +(NSString * _Nonnull)getStringWithMyString:(NSString * _Nonnull)myString __attribute__((warn_unused_result("")));
        [Static]
        [Export("getStringWithMyString:")]
        string GetString(string myString);

        // +(void)setupWithKey:(NSString * _Nonnull)key url:(NSString * _Nonnull)url;
        [Static]
        [Export("setupWithKey:url:")]
        void Setup(string key, string url);

        // +(void)setViewWithView:(NSString * _Nonnull)view;
        [Static]
        [Export("setViewWithView:")]
        void SetView(string view);

        // +(void)setUserIdWithUserId:(NSString * _Nonnull)userId;
        [Static]
        [Export("setUserIdWithUserId:")]
        void SetUserId(string userId);

        // +(void)setUserNameWithUserName:(NSString * _Nonnull)userName;
        [Static]
        [Export("setUserNameWithUserName:")]
        void SetUserName(string userName);

        // +(void)setUserEmailWithUserEmail:(NSString * _Nonnull)userEmail;
        [Static]
        [Export("setUserEmailWithUserEmail:")]
        void SetUserEmail(string userEmail);

        // +(void)setScreenNameAutoCaptureWithAutoCapture:(BOOL)autoCapture;
        [Static]
        [Export("setScreenNameAutoCaptureWithAutoCapture:")]
        void SetScreenNameAutoCapture(bool autoCapture);

        // +(void)setCrashReportingWithCrashReporting:(BOOL)crashReporting;
        [Static]
        [Export("setCrashReportingWithCrashReporting:")]
        void SetCrashReporting(bool crashReporting);

		// +(void)setCustomEventWithEventName:(NSString * _Nonnull)eventName;
        [Static]
        [Export("setCustomEventWithEventName:")]
        void SetCustomEvent(string eventName);
    }
}
