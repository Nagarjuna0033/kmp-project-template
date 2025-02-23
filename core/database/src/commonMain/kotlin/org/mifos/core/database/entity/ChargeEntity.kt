/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.database.entity

import org.mifos.core.database.Entity
import org.mifos.core.database.PrimaryKey

@Entity(
    tableName = "charges",
    indices = [],
    inheritSuperIndices = false,
    primaryKeys = [],
    foreignKeys = [],
    ignoredColumns = [],
)
data class ChargeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val clientId: Int? = null,
    val chargeId: Int? = null,
    val name: String? = null,
    val dueDate: ArrayList<Int?> = arrayListOf(),
    val chargeTimeType: ChargeTimeTypeEntity? = null,
    val amount: Double = 0.0,
    val amountPaid: Double = 0.0,
    val amountWaived: Double = 0.0,
    val amountWrittenOff: Double = 0.0,
    val amountOutstanding: Double = 0.0,
    val penalty: Boolean = false,
    val isActive: Boolean = false,
    val isChargePaid: Boolean = false,
    val isChargeWaived: Boolean = false,
    val paid: Boolean = false,
    val waived: Boolean = false,
)
