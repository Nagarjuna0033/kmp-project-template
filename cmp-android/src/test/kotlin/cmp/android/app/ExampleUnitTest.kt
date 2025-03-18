/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package cmp.android.app

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mifos.core.common.di.AppDispatchers
import org.mifos.testing.di.TestDispatchersModule
import kotlin.test.assertEquals

class ExampleUnitTest : KoinTest {

    private val testDispatcher: TestDispatcher by inject()
    private val defaultDispatcher: CoroutineDispatcher by inject(named(AppDispatchers.Default.name))
    private val ioDispatcher: CoroutineDispatcher by inject(named(AppDispatchers.IO.name))

    @Before
    fun setup() {
        startKoin { modules(TestDispatchersModule) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun isAdditionCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `TestDispatcher should inject UnconfinedTestDispatcher`() = runTest {
        assertEquals(
            UnconfinedTestDispatcher()::class.simpleName,
            testDispatcher::class.simpleName,
        )
    }

    @Test
    fun `ioDispatcher should inject StandardTestDispatcher`() = runTest {
        assertEquals(
            StandardTestDispatcher()::class.simpleName,
            ioDispatcher::class.simpleName,
        )
    }

    @Test
    fun `defaultDispatcher should inject StandardTestDispatcher`() {
        assertEquals(
            StandardTestDispatcher()::class.simpleName,
            defaultDispatcher::class.simpleName,
        )
    }
}
