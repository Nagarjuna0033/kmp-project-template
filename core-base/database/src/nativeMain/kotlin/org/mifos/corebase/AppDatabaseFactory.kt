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
import androidx.room.util.findDatabaseConstructorAndInitDatabaseImpl
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class AppDatabaseFactory {
    inline fun <reified T : RoomDatabase> createDatabase(
        databaseName: String,
        noinline factory: () -> T = { findDatabaseConstructorAndInitDatabaseImpl(T::class) },
    ): RoomDatabase.Builder<T> {
        val dbFilePath = documentDirectory() + "/$databaseName"
        return Room.databaseBuilder(
            name = dbFilePath,
            factory = factory,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}
