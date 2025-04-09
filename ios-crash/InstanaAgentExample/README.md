# InstanaAgentExample

This is a test app for iosAgent framework testing.

- iOS version 13.0 or above is fully supported.
- There is very limited support for iOS versions below 13.0. The main purpose is to test crash catching, make sure it's disabled.
- If run on device, replace test bundle id com.instana.ios.InstanaAgentExample with a real one otherwise certain features like crash catching will not work.

## Steps to Run the application

1. Open [InstanaAgentExample.xcodeproj](../InstanaAgentExample.xcodeproj/) on Xcode. 
2. Add [iOSAgent](https://www.ibm.com/docs/en/instana-observability/1.0.286?topic=instana-monitoring-mobile-applications#ios).
3. Build and Run the App.
4. Open [Config.swift](./Config.swift) and replace these two values with your own (obtained in your Instana Dashboard): 
    -  `INSTANA_REPORTING_KEY`
    - `INSTANA_REPORTING_URL`

## Crash Test

To send a crash:
- Run the application on a real device, replace the test id com.instana.ios.InstanaAgentExample with a real one.
- Navigate to the 'JSON' tab. 
- Click the '+' icon to generate a crash.

<img src="./../images/iOSCrash.png" alt="Instana iOS Agent example" width="300">