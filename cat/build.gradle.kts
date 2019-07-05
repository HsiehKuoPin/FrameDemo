import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
//apply("build.base.gradle.kts")
//apply(plugin = "com.android.application")
//apply(plugin = "kotlin-android")
//apply(plugin = "kotlin-android-extensions")
//apply{
//    plugin("com.android.application")
//    plugin("kotlin-android")
//    plugin("kotlin-android-extensions")
//}

android{
    compileSdkVersion(Vers.compileSdkV)

    defaultConfig {
        applicationId = "com.onechat.cat"
        minSdkVersion(Vers.minSdkV)
        targetSdkVersion(Vers.targetSdkV)
        versionCode = Vers.vCode
        versionName = Vers.vName

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        val appProFile = file("../local.properties")
        val properties = Properties()
        properties.load(appProFile.inputStream())
        create("release") {
            storeFile = file(properties["JKS_NAME"].toString())
            storePassword = properties["STORE_PASSWORD"].toString()
            keyAlias = properties["KEY_ALIAS"].toString()
            keyPassword = properties["KEY_PASSWORD"].toString()
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs?.getByName("release")
        }

        create("preview") {
            isMinifyEnabled = false
            isZipAlignEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs?.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }

        getByName("release") {
            initWith(buildTypes.findByName("preview"))
        }

    }

    compileOptions {
        setSourceCompatibility("1.8")
        setTargetCompatibility("1.8")
    }

    androidExtensions {
        isExperimental = true
//        configure(delegateClosureOf<AndroidExtensionsExtension>{
//            isExperimental = true
//        })
    }

}

dependencies{
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Vers.kotlinV}")
    implementation("com.android.support:appcompat-v7:${Vers.supportLibV}")
    implementation("com.android.support:recyclerview-v7:${Vers.supportLibV}")
    implementation("com.android.support:design:${Vers.supportLibV}")
    implementation("com.android.support:support-v4:${Vers.supportLibV}")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation ("android.arch.lifecycle:extensions:1.1.1")
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("com.android.support.test:runner:1.0.2")
    androidTestImplementation ("com.android.support.test.espresso:espresso-core:3.0.2")

    implementation (project(":benjaminlib"))
    implementation ("com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.46")
    implementation ("com.danikula:videocache:2.7.1")
    implementation ("com.youth.banner:banner:1.4.10")
    implementation ("com.github.bumptech.glide:glide:4.9.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.9.0")
}
