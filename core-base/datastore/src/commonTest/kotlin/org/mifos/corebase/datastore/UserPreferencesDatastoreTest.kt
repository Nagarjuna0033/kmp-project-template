/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.corebase.datastore

import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.serializer
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mifos.testing.data.usersTestData
import org.mifos.testing.di.TestCoreDataStoreModule
import org.mifos.testing.di.TestDispatchersModule
import org.mifos.testing.model.TestUserData
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserPreferencesDatastoreTest : KoinTest {
    private val datastore: UserPreferencesDataStore by inject()
    private val testDispatcher: TestCoroutineScheduler by inject()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(TestCoreDataStoreModule, TestDispatchersModule)
        }
    }

    @Test
    fun testPutAndGetStringValue() = runTest(testDispatcher) {
        datastore.putValue("testKey", "testValue")
        val retrievedValue = datastore.getValue("testKey", "")
        assertEquals("testValue", retrievedValue)
    }

    @Test
    fun testPutAndGetTestUserData() = runTest(testDispatcher) {
        val userData = usersTestData.first()
        datastore.putValue("testUser", userData, serializer<TestUserData?>())
        val retrievedUserData = datastore.getValue("testUser", null, serializer<TestUserData?>())

        assertEquals(userData, retrievedUserData)
    }

    @Test
    fun testDefaultValueWhenKeyNotExists() = runTest(testDispatcher) {
        val retrievedValue = datastore.getValue("non_existent_key", "DefaultValue")
        assertEquals("DefaultValue", retrievedValue) // Should return the default
    }

    @Test
    fun testRemoveValue() = runTest(testDispatcher) {
        datastore.putValue("test_key", "To be deleted")
        datastore.removeValue("test_key")

        val retrievedValue = datastore.getValue("test_key", "DefaultValue")
        assertEquals("DefaultValue", retrievedValue) // Should return default after removal
    }

    @Test
    fun testClearAll() = runTest(testDispatcher) {
        datastore.putValue("key1", "Value1")
        datastore.putValue("key2", "Value2")

        datastore.clearAll()

        val value1 = datastore.getValue("key1", "Default")
        val value2 = datastore.getValue("key2", "Default")

        assertEquals("Default", value1)
        assertEquals("Default", value2) // Both should return default
    }

    @Test
    fun testGetSizeAndGetAllKeys() = runTest(testDispatcher) {
        datastore.putValue("key1", "Value1")
        datastore.putValue("key2", "Value2")

        val allKeys = datastore.getAllKeys()

        assertEquals(2, datastore.getSize())
        assertTrue { "key1" in allKeys }
        assertTrue { "key2" in allKeys }
    }

    @Test
    fun testHasKey() = runTest(testDispatcher) {
        datastore.putValue("key1", "Value1")

        val hasKey1 = datastore.hasKey("key1")
        val hasKey2 = datastore.hasKey("key2")

        assertTrue(hasKey1)
        assertFalse(hasKey2)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}
