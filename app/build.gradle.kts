/*
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.myresumeapp"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.myresumeapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.firebase.database)
    // Supabase BOM
    implementation(platform("io.github.jan-tennert.supabase:bom:3.5.0"))

    // Add Storage module
    implementation("io.github.jan-tennert.supabase:storage-kt")

    // (Keep your other modules like postgrest-kt or auth-kt here)
        // Firebase (Realtime Database + Storage + Auth)
        implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
        implementation("com.google.firebase:firebase-database-ktx")
        implementation("com.google.firebase:firebase-storage-ktx")
        implementation("com.google.firebase:firebase-auth-ktx")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

        // PDF Generation
        implementation("com.itextpdf:itextg:5.5.10")

        // Coil for image loading
        implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("io.ktor:ktor-client-okhttp:3.5.1")
    implementation(platform("io.github.jan-tennert.supabase:bom:3.8.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")  // database
    implementation("io.github.jan-tennert.supabase:auth-kt")       // login (was gotrue-kt before 3.0)
    implementation("io.github.jan-tennert.supabase:storage-kt")    // file uploads, if needed
    implementation("io.github.jan-tennert.supabase:realtime-kt")   // live updates, if needed
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.12.0")

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.myresumeapp"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.myresumeapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.firebase.database)
    // Supabase BOM
    implementation(platform("io.github.jan-tennert.supabase:bom:3.5.0"))

    // Add Storage module
    implementation("io.github.jan-tennert.supabase:storage-kt")

    // (Keep your other modules like postgrest-kt or auth-kt here)
    // Firebase (Realtime Database + Storage + Auth)
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // PDF Generation
    implementation("com.itextpdf:itextg:5.5.10")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("io.ktor:ktor-client-okhttp:3.5.1")
    implementation(platform("io.github.jan-tennert.supabase:bom:3.8.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")  // database
    implementation("io.github.jan-tennert.supabase:auth-kt")       // login (was gotrue-kt before 3.0)
    implementation("io.github.jan-tennert.supabase:storage-kt")    // file uploads, if needed
    implementation("io.github.jan-tennert.supabase:realtime-kt")   // live updates, if needed
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.12.0")

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}