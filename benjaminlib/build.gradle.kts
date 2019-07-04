plugins {
    id("com.android.library")
    id("kotlin-android")
}
android {
            compileSdkVersion(Vers.compileSdkV)
    defaultConfig {
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
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")

    api ("com.google.code.gson:gson:2.8.4")
    api ("io.reactivex.rxjava2:rxandroid:2.1.0")
    api ("io.reactivex.rxjava2:rxjava:2.2.2")
    api ("com.squareup.retrofit2:retrofit:${Vers.retrofitSdkV}")
    api ("com.squareup.retrofit2:converter-gson:${Vers.retrofitSdkV}")
    api ("com.squareup.retrofit2:adapter-rxjava2:${Vers.retrofitSdkV}")
    api ("com.squareup.okhttp3:logging-interceptor:3.6.0")
    api ("com.orhanobut:logger:2.2.0")
    api ("com.squareup.picasso:picasso:2.5.2")
    api ("net.lingala.zip4j:zip4j:1.3.2")
}
