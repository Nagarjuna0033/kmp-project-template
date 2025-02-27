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

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class AppDatabaseFactory(
    private val context: Context,
) {
    fun <T : RoomDatabase> createDatabase(databaseClass: Class<T>, databaseName: String): RoomDatabase.Builder<T> {
        return Room.databaseBuilder(
            context.applicationContext,
            databaseClass,
            databaseName,
        )
    }
}
