


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.ksp)
    //alias(libs.plugins.androidx.room)

}


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
       // iosX64(),
        iosArm64(),
        //iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    sourceSets {

        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.koin.androidx.compose)

            implementation(libs.ktor.client.okhttp)
            // socket.io
            api("io.socket:socket.io-client:2.1.0") {
                exclude("org.json", "json")
            }

            // ExoPlayer
            implementation (libs.androidx.media3.exoplayer)
            implementation (libs.androidx.media3.common)
            implementation (libs.androidx.media3.session)
            implementation(libs.androidx.media3.exoplayer.hls)
            implementation(libs.androidx.media3.datasource.okhttp)
            implementation(libs.androidx.media3.ui)


            api(libs.androidx.core.ktx)
            api(libs.androidx.activity.compose)
            api(libs.androidx.appcompat)
            api(libs.androidx.activity)

            api(project.dependencies.platform(libs.firebase.bom))
            api(libs.firebase.analytics)
            api(libs.firebase.crashlytics)

            implementation(compose.preview)
            implementation( "com.github.chuckerteam.chucker:library:4.1.0")
        }

        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.androidx.lifecycle.viewmodel)
            api(libs.androidx.lifecycle.runtime.compose)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
            api(libs.lifecycle.viewmodel)
            api(libs.navigation.compose)

            api(libs.coil.compose)
            api(libs.coil.ktor)
            api(libs.coil.core)
            api(libs.coil.mp)
            api(libs.coil.gif)

            implementation(libs.bundles.ktor)

            // datastore
            api(libs.androidx.datastore.lib)
            api(libs.androidx.datastore.preferences)

            // room
            //implementation(libs.androidx.room.compiler)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.room.sqlite)
            //implementation(libs.androidx.room.gradle.plugin)

            //api("dev.icerock.moko:socket-io:0.6.0")
            implementation(libs.kotlinx.datetime)

            implementation(libs.haze)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
        val commonMain by getting {
            /*kotlin.srcDir(
                buildConfigGenerator.map { it.destinationDir }
            )*/
        }
    }
}

android {
    namespace = "com.example.kmp"
    compileSdk = 35
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {
   /* add("kspAndroid", libs.androidx.room.compiler)
    // add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)*/
    // add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}
