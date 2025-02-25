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

import org.mifos.core.database.dao.SampleDao

expect abstract class AppDatabase {
    abstract val sampleDao: SampleDao
}

// fun getRoomDatabase(
//    appDatabase: AppDatabase,
// ): AppDatabase {
//    return appDatabase
// }
