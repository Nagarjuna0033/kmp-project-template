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

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mifos.core.database.dao.SampleDao
import org.mifos.core.database.entity.SampleEntity
import org.mifos.testing.di.TestDatabaseModule

class SampleDaoTest : KoinTest {
    private val db: AppDatabase by inject()
    private val dao: SampleDao by inject()

    @Before
    fun setup() {
        startKoin {
            modules(TestDatabaseModule)
        }
    }

    @Test
    fun testInsertAndRetrieve() = runBlocking {
        val sampleData = listOf(
            SampleEntity(name = "Sample 1"),
            SampleEntity(name = "Sample 2"),
            SampleEntity(name = "Sample 3"),
        )
        dao.insertSample(sampleData)

        val retrieveData = dao.getAllSamples().first()
        assertEquals(3, sampleData.size)
        sampleData.forEachIndexed { index, data ->
            assertEquals(data.name, retrieveData[index].name)
        }
    }

    @After
    fun tearDown() {
        db.close()
        stopKoin()
    }
}
