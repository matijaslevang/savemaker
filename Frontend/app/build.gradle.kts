import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

fun getIpAddress(): String? {
    val properties = Properties()
    val localPropertiesFile = File("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }
    }
    return properties.getProperty("ip_addr")
}

android {
    namespace = "com.example.savemaker"
    compileSdk = 35

    defaultConfig {

        applicationId = "com.example.savemaker"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "IP_ADDR", "\""+getIpAddress()+"\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    viewBinding {
        enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.squareup.retrofit2:retrofit:2.3.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.3.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")
}