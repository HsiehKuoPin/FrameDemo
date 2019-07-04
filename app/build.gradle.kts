plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
android {
    compileSdkVersion(Vers.compileSdkV)

    defaultConfig {
        applicationId = "com.onechat.cat"
        minSdkVersion(Vers.minSdkV)
        targetSdkVersion(Vers.targetSdkV)
        versionCode = Vers.vCode
        versionName = Vers.vName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes{
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Vers.kotlinV}")
    implementation("com.android.support:appcompat-v7:${Vers.supportLibV}")
    implementation("com.android.support:recyclerview-v7:${Vers.supportLibV}")
    implementation("com.android.support:design:${Vers.supportLibV}")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")
    implementation (project(":benjaminlib"))
    implementation ("com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.46")
}
