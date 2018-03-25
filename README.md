## Tests valid for current Wikipedia app built from master at commit 2124df9b7c85cb535eaf40c8061f25736567c74a

# wiki-automation
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
* These tests have been developed and tested on the Moto G (Android 5) which I gifted my Mrs and was duely gifted back couple of years ago.
* Will launch Appium server automatically when application test context is loaded.
* To execute
  * connect an android device or run an emulator
  * run, mvn test or directly from the IDE by right clicking on the tests package.
