/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.core.datastore

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

const val USER_KEY = "sample_user"

@Suppress("TooManyFunctions")
@OptIn(ExperimentalSettingsApi::class)
class UserPreferencesDataStore(
    private val suspendSettings: SuspendSettings,
    val flowSettings: FlowSettings,
) {

    // --- Basic Operations ---

    // Store primitive value

    suspend fun putInt(key: String, value: Int) {
        suspendSettings.putInt(key, value)
    }

    suspend fun getInt(key: String, default: Int): Int {
        return suspendSettings.getInt(key, default)
    }

    suspend fun getNullableInt(key: String): Int? {
        return suspendSettings.getIntOrNull(key)
    }

    suspend fun putLong(key: String, value: Long) {
        suspendSettings.putLong(key, value)
    }

    suspend fun getLong(key: String, default: Long): Long {
        return suspendSettings.getLong(key, default)
    }

    // Retrieve nullable primitive
    suspend fun getNullableLong(key: String): Long? {
        return suspendSettings.getLongOrNull(key)
    }

    suspend fun putFloat(key: String, value: Float) {
        suspendSettings.putFloat(key, value)
    }

    suspend fun getFloat(key: String, default: Float): Float {
        return suspendSettings.getFloat(key, default)
    }

    suspend fun getNullableFloat(key: String): Float? {
        return suspendSettings.getFloatOrNull(key)
    }

    suspend fun putDouble(key: String, value: Double) {
        suspendSettings.putDouble(key, value)
    }

    suspend fun getDouble(key: String, default: Double): Double {
        return suspendSettings.getDouble(key, default)
    }

    suspend fun getNullableDouble(key: String): Double? {
        return suspendSettings.getDoubleOrNull(key)
    }

    suspend fun putString(key: String, value: String) {
        suspendSettings.putString(key, value)
    }

    suspend fun getString(key: String, default: String): String {
        return suspendSettings.getString(key, default)
    }

    suspend fun getNullableString(key: String): String? {
        return suspendSettings.getStringOrNull(key)
    }

    suspend fun putBoolean(key: String, value: Boolean) {
        suspendSettings.putBoolean(key, value)
    }

    suspend fun getBoolean(key: String, default: Boolean): Boolean {
        return suspendSettings.getBoolean(key, default)
    }

    suspend fun getNullableBoolean(key: String): Boolean? {
        return suspendSettings.getBooleanOrNull(key)
    }

    // Check key existence
    suspend fun hasKey(key: String): Boolean {
        return suspendSettings.hasKey(key)
    }

    suspend fun removeValue(key: String) {
        suspendSettings.remove(key)
    }

    // Clear all
    suspend fun clearAll() {
        suspendSettings.clear()
    }

    // Get all keys and size
    suspend fun getAllKeys(): Set<String> {
        return suspendSettings.keys()
    }

    suspend fun getSize(): Int {
        return suspendSettings.size()
    }

    // --- Serialization Operations ---

    // Store serialized object
    suspend fun <T> putSerializableData(key: String, value: T, serializer: KSerializer<T>) {
        val json = Json.encodeToString(
            serializer = serializer,
            value = value,
        )
        suspendSettings.putString(key = key, value = json)
    }

    //    Get serialized object with default value
    suspend fun <T> getSerializedData(
        key: String,
        defaultValue: T,
        serializer: KSerializer<T>,
    ): T {
        val json = suspendSettings.getStringOrNull(key = key) ?: return defaultValue
        return Json.decodeFromString(
            deserializer = serializer,
            string = json,
        )
    }

    // --- Listener Operations ---

    inline fun <reified T> observeKeyFlow(
        key: String,
        defaultValue: T,
        serializer: KSerializer<T>?,
    ): Flow<T> {
        return when (T::class) {
            Int::class -> flowSettings.getIntFlow(key, defaultValue as Int) as Flow<T>
            Long::class -> flowSettings.getLongFlow(key, defaultValue as Long) as Flow<T>
            Float::class -> flowSettings.getFloatFlow(key, defaultValue as Float) as Flow<T>
            Double::class -> flowSettings.getDoubleFlow(key, defaultValue as Double) as Flow<T>
            String::class -> flowSettings.getStringFlow(key, defaultValue as String) as Flow<T>
            Boolean::class -> flowSettings.getBooleanFlow(key, defaultValue as Boolean) as Flow<T>
            else -> {
                require(serializer != null) { "Unsupported type or no serializer provided for ${T::class}" }
                flowSettings.getStringFlow(key, Json.encodeToString(serializer, defaultValue))
                    .map { jsonString ->
                        Json.decodeFromString(serializer, jsonString)
                    }
            }
        }
    }

    inline fun <reified T> observeNullableKeyFlow(
        key: String,
        serializer: KSerializer<T>?,
    ): Flow<T?> {
        return when (T::class) {
            Int::class -> flowSettings.getIntOrNullFlow(key) as Flow<T?>
            Long::class -> flowSettings.getLongOrNullFlow(key) as Flow<T?>
            Float::class -> flowSettings.getFloatOrNullFlow(key) as Flow<T?>
            Double::class -> flowSettings.getDoubleOrNullFlow(key) as Flow<T?>
            String::class -> flowSettings.getStringOrNullFlow(key) as Flow<T?>
            Boolean::class -> flowSettings.getBooleanOrNullFlow(key) as Flow<T?>
            else -> {
                require(serializer != null) { "Unsupported type or no serializer provided for ${T::class}" }
                flowSettings.getStringOrNullFlow(key)
                    .map { jsonString ->
                        jsonString?.let { Json.decodeFromString(serializer, jsonString) }
                    }
            }
        }
    }
}
