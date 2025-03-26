/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
plugins {
    alias(libs.plugins.kmp.library.convention)
    id("kotlinx-serialization")
}

android {
    namespace = "org.mifos.testing"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.coreBase.datastore)
            api(libs.kotlinx.coroutines.test)
            api(libs.kotlin.test)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.test)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
            implementation(libs.koin.test)
        }
    }
}