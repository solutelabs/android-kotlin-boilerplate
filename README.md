# Android base Scaffold Project

This is a base Android scaffold project that has all configuration & setup created. So we can reduce the efforts of basic project setup & project level boilerplate code.

## Features 

### Flavors
Production, Staging, Development (with abstract properties)
Modes: Debug, Release.

### Custom Logger (Default Sentry)
Abstract layer for a custom logger. It has default implementation of [Sentry](https://sentry.io/welcome/).
It can again be replaced with any other services.

### Firebase Crashalytics
Crashalytics setup that works in Release Mode.

### Analytics (Default Firebase)
Abstract layer for a Custom event logging. It has a default implementation of Firebase Analytics. It can again be replaced with any other services.

### Firebase Performance Monitor
Firebase performance integration to check App freezing, network latency monitoring.

### Remote Config
Integration of firebase remote config.

### Flavor-wise installable build
Flavor-wise application-id overridden. So that the same application with different flavors can be installed simultaneously.

### App Update Prompts (Flexible + Immediate)
Force upgrade setup based on Firebase Remote config.

Currently, it has two configuration
1. Latest App Version (Build Number)
2. Latest Stable App Version (Build Number)

If the latest app version is higher then the current version, it will trigger Flexible Update (Skipable).

If the current app version is below the latest stable version, then it will trigger an Immediate (Force) update.


## Base Activity
Coroutines based Start Activity For Result and Permission manager implementation


### Other Useful dependencies
- Retrofit
- Kotlin Coroutines
- Kotlinx Serialization
- Jetpack Room
- Jetpack Paging
- Android KTX
- Material Themes