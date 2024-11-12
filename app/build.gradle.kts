plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.bookshelfapp"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.bookshelfapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.coil)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation ("androidx.appcompat:appcompat:1.6.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Converter for Gson (or any other converter you're using)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutine support for Retrofit
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")  // If you're using RxJava, for coroutines just retrofit is enough



    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")



    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
}

