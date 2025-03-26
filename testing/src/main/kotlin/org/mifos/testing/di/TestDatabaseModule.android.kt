/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.testing.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mifos.core.database.AppDatabase

actual val testPlatformModule: Module = module {
    single {
        Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).build()
    }
}
