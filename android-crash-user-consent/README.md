# Kotlin Example for Instana Android Agent

This app showcases the simplest use case of the Instana Android Agent in an Android app written using:
- Kotlin
- Coroutines
- OkHttp3

This app enables crash reporting with user consent dialog.
The crash generated in the example app is reported and can be seen in the dashboard .

Before generating crash a dialog box appears for user consent:

<img width="300" alt="Instana Android Agent example" src="images/Before generating crash a dialog box appears for user consent.png">

If yes is tapped then the alert box appears as shown below

<img width="300" alt="Instana Android Agent example" src="images/If yes is tapped then the alert box appears as shown below.png">

Two options are available:

<img width="300" alt="Instana Android Agent example" src="images/Two options are available-.png">

When generate crash button is clicked the app crashes:

<img width="300" alt="Instana Android Agent example" src="images/When generate crash button is clicked the app crashes-.png">

After the first launch, if the app is launched again and
if yes was tapped in the first launch then it will displayed as shown below

<img width="300" alt="Instana Android Agent example" src="images/After the first launch, if the app is launched again and.png">

if no was tapped in the first launch then the generate crash button will be disabled as shown  below

<img width="300" alt="Screenshot 2023-07-26 at 4 03 09 PM" src="images/if no was tapped in the first launch then the generate crash .png">

## Compiling

Open [ExampleApp.kt](app/src/main/java/com/instana/android/example/ExampleApp.kt) and replace these two values with your own (obtained in your Instana Dashboard): 
1. `REPORTING_URL`
2. `KEY`

Compile as usual using Android Studio 3.x

## Structure

- Instana dependencies in [project-level gradle.build](build.gradle)
- Instana plugin and dependencies in [module-level gradle.build](app/build.gradle)
- Minimal initialization of Instana Agent in [ExampleApp.kt](app/src/main/java/com/instana/android/example/ExampleApp.kt)
- Set view name in `onResume()` in [MainActivity.kt](app/src/main/java/com/instana/android/example/MainActivity.kt)
- OkHttp3 and coroutines to execute a single query which Instana automatically tracks
