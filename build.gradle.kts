// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
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

