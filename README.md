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


### Base Activity
Coroutines based Start Activity For Result and Permission manager implementation


### Other Useful dependencies
- Retrofit
- Kotlin Coroutines
- Kotlinx Serialization
- Jetpack Room
- Jetpack Paging
- Android KTX
- Material Themes

### CI / CD

#### Codemagic

Codemagic.yaml file has been added at the root of source code. It contains sample pipelines for different build environments.

###### How to build?

1. Connect the project repository to codemagic using [this guide](https://docs.codemagic.io/getting-started/adding-apps-from-custom-sources/). Codemagic will auto-detect the yaml file which has sample workflow added.
2. Select workflow and the branch from which buid to be generated. Start Build and it should generate the build.

###### What to be configured?
- Except `production-store` workflow, all the workflows are configured to use Debug keystore generated on the fly from Codemagic's build machine.
- For `production-store` workflow, the keystore related environment variables must be replaced with the project specific keystore info.

###### How to distribute?
Once codemagic generates the artifacts, we can distribute the apps in various ways.

1. Using Firebase App Distribution. [More info](https://firebase.google.com/docs/app-distribution).
One can automate the firebase distribution as mentioned [here](https://docs.codemagic.io/publishing-yaml/distribution/#publishing-an-app-to-firebase-app-distribution).
2. Using platforms like [Diawi](https://www.diawi.com/)
3. Sharing raw apk to user.