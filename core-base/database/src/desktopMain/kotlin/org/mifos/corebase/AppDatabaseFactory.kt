/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.corebase

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.util.findAndInstantiateDatabaseImpl
import java.io.File

class AppDatabaseFactory {
    inline fun <reified T : RoomDatabase> createDatabase(
        databaseName: String,
        noinline factory: () -> T = { findAndInstantiateDatabaseImpl(T::class.java) },
    ): RoomDatabase.Builder<T> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "MifosDatabase")
            os.contains("mac") -> File(userHome, "Library/Application Support/MifosDatabase")
            else -> File(userHome, ".local/share/MifosDatabase")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, databaseName)

        return Room.databaseBuilder(
            name = dbFile.absolutePath,
            factory = factory,
        )
    }
}
