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

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import org.mifos.core.database.dao.SampleDao
import org.mifos.core.database.entity.SampleEntity
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
class SampleDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: SampleDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.sampleDao
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
    }
}
