@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hiltAndroid)
    id(libs.plugins.instana.plugin.get().pluginId)
}

android {
    namespace = "com.ibm.instashop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ibm.instashop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.4.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    kapt(libs.hilt.compiler)
    implementation(libs.instana.runtime)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.navigation)
    implementation(libs.material)
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.logging.interceptor)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.retrofit.core)
    implementation(libs.coil.compose)
    implementation(libs.glide)
    implementation(libs.work.manager)
    implementation(libs.accompanist.pager)
    testImplementation(libs.junit)
    implementation(libs.constraintlayout.compose)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}