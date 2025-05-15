// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false

    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    alias(libs.plugins.ksp) apply false
    //alias(libs.plugins.androidx.room) apply false
    //alias(libs.plugins.google.services) apply false
    //alias(libs.plugins.firebase.crashlytics) apply false
}