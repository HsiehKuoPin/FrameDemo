// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlinV}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }

//    tasks.withType(JavaCompile) {
//        options.encoding = "UTF-8"//设置全局编码
//        sourceCompatibility = JavaVersion.VERSION_1_8//设置全局编译器的版本
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
}
task<Delete>(name = "clean"){
    delete(rootProject.buildDir)
}

//task("clean", Delete::class){
//    delete = setOf(rootProject.buildDir)
//}
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}

ext {
    set("kotlinVersion", "1.2.71")
    set("compileSdkVersion",28)
    set("minSdkVersion",19)
    set("targetSdkVersion",28)
    set("supportLibVersion","28.0.0")
    set("versionCode",1)
    set("versionName","1.0.0")
    set("retrofitSdkVersion","2.4.0")
}
