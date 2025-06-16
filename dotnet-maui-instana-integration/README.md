# .NET MAUI with Instana Android & iOS Agent

This is a sample application to demonstrate how to integrate the Instana native agent with a .NET MAUI application. There are two native agents from Instana (iOS & Android) that need to be handled separately and implemented together in the application.

## Prerequisites

Before you begin, ensure that you have the following setup:

- Visual Studio Code (VSC) with the MAUI plugin or Visual Studio.
- .NET SDK version 9.0.
- Xcode for running the iOS application (Simulator).
- Android Emulator (install with Android Studio).
- Android SDK path set in `ANDROID_HOME`.
- JDK 17.0.12.

## Steps to Run the Application

Follow these steps to build and run the application:

1. Open the `dotnet-maui-instana-integration` folder in Visual Studio or Visual Studio Code.
2. Open the terminal within your IDE and navigate to the path `android/native/`:
   ```bash
   cd android/native/
3. run (For Mac)
    ```bash
    ./gradlew build 
4. Next, navigate to [android/NewBinding.Android.Binding/](android/NewBinding.Android.Binding/) and run:
   ```bash
   cd android/NewBinding.Android.Binding/
5. Build the Android bindings with .NET: 
   ```bash
   dotnet build
6. Next, navigate to [macios/NewBinding.MaciOS.Binding/](macios/NewBinding.MaciOS.Binding/) and run:
   ```bash
   cd macios/NewBinding.MaciOS.Binding/
7. Build the iOS bindings with .NET:
   ```bash
   dotnet build
 If you encounter any build issues at this step, open Xcode with the macios/native project and add InstanaAgent as a package dependency.
Now, you are ready to run the sample application.
8. You can either run the [MainPage.xaml.cs](sample/MainPage.xaml.cs) file directly from your IDE on any platform of your choice or run from the terminal using the following commands:
- For Android:
    ```bash
    dotnet build -t:run -f:net9.0-android
- For iOS:
    ```bash
    dotnet build -t:run -f:net9.0-ios
# Instana APIs

 - For Android the APIs are exposed at [android/native/newbinding/src/main/java/com/example/newbinding/DotnetNewBinding.java](android/native/newbinding/src/main/java/com/example/newbinding/DotnetNewBinding.java). 
 - For iOS the APIs are exposed at [macios/native/NewBinding/NewBinding/DotnetNewBinding.swift](macios/native/NewBinding/NewBinding/DotnetNewBinding.swift).
 - For iOS seperate binding is required at [macios/NewBinding.MaciOS.Binding/ApiDefinition.cs](macios/NewBinding.MaciOS.Binding/ApiDefinition.cs). 
 - For more details on the APIs please refer - [Android](https://www.ibm.com/docs/en/instana-observability/current?topic=applications-android-api) / [iOS](https://www.ibm.com/docs/en/instana-observability/current?topic=applications-ios-api)

 # Limitations
 - For Android, network calls are not auto-captured, as plugin injection has not been implemented. You can track the issue here: [Issue](https://github.com/CommunityToolkit/Maui.NativeLibraryInterop/issues/83)
 - On iOS, some library calls may not be auto-captured when API calls are made with browser-based requests. However, all other network requests will be auto-collected.
 - Crash collection is not supported as background services are not active after a crash.

 ### 📌 Special Note

> ⚠️ **This repository is provided as an example and workaround for MAUI support.**  
> It is **not an official solution** and is intended for **demonstration purposes only** until official MAUI support is released via the NuGet package.

- **Official support for MAUI** will be available once the corresponding NuGet package is published by our team.
- **Customers should not create Instana official support tickets** based on this repository.
- For any questions, issues, or suggestions related to this example, please use the **[Issues](../../awesome-eum/issues)** section of this GitHub repository.


 
 Happy coding, and may your apps be faster and more reliable than ever before! 🚀
Instana is committed to continuously improving this integration. As more stable solutions become available, we’ll keep you updated and release them on NuGet. Stay tuned for a more polished, foolproof experience!
