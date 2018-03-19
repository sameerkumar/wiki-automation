## Am updating packages and tests to be compatible to the latest version of the app, stay tuned

# wiki-automation (valid for 2016 App)
Appium test suite using Java + Junit + Spring + Page objects

## Requirements
* Maven
* Android SDK API >= 17 (Additional features require 18/19)
* ANDROID_HOME environment variable set to valid value

## Scenarios Automated
* Login - Being one of the commonest features of the app, this feature is an ideal candidate for automated regression tests.
* Search - Another most used feature within the app suitable for automated regression tests.
* Internalisation - The app is used across the globe, hence required to verify that articles are available in supported languages.

## Test
* Set, test.properties
* These tests have been developed and tested on Android Emulator.
* Will launch Appium server automatically when application test context is loaded.
* To execute
  * connect an android device or run an emulator
  * run, mvn test or directly from the IDE by right clicking on the tests package.
