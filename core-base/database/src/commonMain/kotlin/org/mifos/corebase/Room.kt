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

import kotlin.reflect.KClass

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect annotation class Dao()

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.BINARY)
expect annotation class Query(
    val value: String,
)

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
expect annotation class Insert(
    val entity: KClass<*>,
    val onConflict: Int,
)

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
expect annotation class PrimaryKey(
    val autoGenerate: Boolean,
)

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(allowedTargets = [])
@Retention(AnnotationRetention.BINARY)
expect annotation class ForeignKey

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(allowedTargets = [])
@Retention(AnnotationRetention.BINARY)
expect annotation class Index

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Entity(
    val tableName: String,
    val indices: Array<Index>,
    val inheritSuperIndices: Boolean,
    val primaryKeys: Array<String>,
    val foreignKeys: Array<ForeignKey>,
    val ignoredColumns: Array<String>,
)

object OnConflictStrategy {
    const val NONE = 0
    const val REPLACE = 1
    const val ROLLBACK = 2
    const val ABORT = 3
    const val FAIL = 4
    const val IGNORE = 5
}
