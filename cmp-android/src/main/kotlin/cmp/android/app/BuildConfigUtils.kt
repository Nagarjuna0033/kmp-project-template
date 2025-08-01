/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package cmp.android.app

import android.os.Build

/**
 * A boolean property that indicates whether the current build is a dev build.
 */
val isDevBuild: Boolean
    get() = BuildConfig.BUILD_TYPE == "debug"

/**
 * A string that represents a displayable app version.
 */
val versionData: String
    get() = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"

/**
 * A string that represents device data.
 */
val deviceData: String get() = "$deviceBrandModel $osInfo $buildInfo"

/**
 * A string representing the build flavor or blank if it is the standard configuration.
 */
private val buildFlavorName: String
    get() = when (BuildConfig.FLAVOR) {
        "demo" -> ""
        else -> "-${BuildConfig.FLAVOR}"
    }

/**
 * A string representing the build type.
 */
private val buildTypeName: String
    get() = when (BuildConfig.BUILD_TYPE) {
        "debug" -> "dev"
        "release" -> "prod"
        else -> BuildConfig.BUILD_TYPE
    }

/**
 * A string representing the device brand and model.
 */
private val deviceBrandModel: String get() = "\uD83D\uDCF1 ${Build.BRAND} ${Build.MODEL}"

/**
 * A string representing the operating system information.
 */
private val osInfo: String get() = "\uD83E\uDD16 ${Build.VERSION.RELEASE}@${Build.VERSION.SDK_INT}"

/**
 * A string representing the build information.
 */
private val buildInfo: String
    get() = "\uD83D\uDCE6 $buildTypeName" +
        buildFlavorName.takeUnless { it.isBlank() }?.let { " $it" }.orEmpty()
