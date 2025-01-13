plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.hilt.plugin)
    kotlin("kapt")
    alias(libs.plugins.compose.compiler)




}

android {
    namespace = "com.example.medicalstoreuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.medicalstoreuser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.airbnb.android:lottie-compose:6.1.0")
    implementation ("androidx.compose.ui:ui:1.7.5")
    implementation("io.coil-kt:coil-compose:2.7.0")



    // Room database
    //implementation ("androidx.room:room-runtime:2.6.1")
    implementation (libs.androidx.room.runtime)


    // For Room annotations like @Database, @Dao, @Entity
   // kapt ("androidx.room:room-compiler:2.6.1")
    kapt(libs.androidx.room.compiler)


    // Optional - Kotlin extensions and Coroutines support for Room
    //implementation ("androidx.room:room-ktx:2.6.1")
    implementation (libs.androidx.room.ktx)



}