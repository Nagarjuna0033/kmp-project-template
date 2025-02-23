/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.database.dao

import kotlinx.coroutines.flow.Flow
import org.mifos.core.database.Dao
import org.mifos.core.database.Insert
import org.mifos.core.database.OnConflictStrategy
import org.mifos.core.database.Query
import org.mifos.core.database.entity.ChargeEntity

@Dao
interface ChargeDao {

    @Query("SELECT * FROM charges")
    fun getAllLocalCharges(): Flow<List<ChargeEntity>>

    @Insert(entity = ChargeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharge(charge: List<ChargeEntity>)

    @Insert(entity = ChargeEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun syncCharges(charges: List<ChargeEntity>)
}
