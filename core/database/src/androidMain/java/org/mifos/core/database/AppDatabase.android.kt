/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.mifos.core.database.dao.ChargeDao
import org.mifos.core.database.entity.ChargeEntity
import org.mifos.core.database.utils.ChargeTypeConverters

@Database(
    entities = [
        ChargeEntity::class,
    ],
    version = AppDatabase.VERSION,
    exportSchema = true,
    autoMigrations = [],
)
@TypeConverters(ChargeTypeConverters::class)
actual abstract class AppDatabase : RoomDatabase() {
    actual abstract val chargeDao: ChargeDao

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "mifos_database.db"
    }
}
