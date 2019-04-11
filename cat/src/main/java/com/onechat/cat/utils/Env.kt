package com.onechat.cat.utils

import com.onechat.cat.BuildConfig

object Env {

    const val RELEASE = "release"

    const val PREVIEW = "preview"

    const val DEBUG = "debug"

    private var gradleBuildType: String = BuildConfig.BUILD_TYPE

//    fun isDevIng(): Boolean {
//        return BuildConfig.IS_DEV_ING
//    }

    fun isDebug(): Boolean {
        return isThe(DEBUG)
    }

    fun isPreview(): Boolean {
        return isThe(PREVIEW)
    }

    fun isRelease(): Boolean {
        return isThe(RELEASE)
    }

    fun buildType(): String {
        return gradleBuildType
    }

    fun isNotRelease(): Boolean {
        return isRelease().not()
    }

    fun isNotDebug(): Boolean {
        return isDebug().not()
    }

    private fun isThe(buildType: String?): Boolean {
        return buildType == gradleBuildType
    }

}