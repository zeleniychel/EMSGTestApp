plugins {
    alias(libs.plugins.android.application)
    id ("kotlin-parcelize")
    kotlin("kapt")
    id ("com.google.dagger.hilt.android")
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.emsgtestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.emsgtestapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.retrofit)
    implementation(libs.dagger.hilt.android)
    kapt(libs.hilt.compiler)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.play.services)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}