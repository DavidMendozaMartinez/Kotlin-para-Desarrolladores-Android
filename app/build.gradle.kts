import org.jetbrains.kotlin.konan.util.visibleName

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId("com.davidmendozamartinez.myplayer")
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.core:core-ktx:1.3.2")

    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    implementation("org.koin:koin-androidx-viewmodel:2.2.0-rc-3")

    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-inline:2.28.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

repositories {
    mavenCentral()
}