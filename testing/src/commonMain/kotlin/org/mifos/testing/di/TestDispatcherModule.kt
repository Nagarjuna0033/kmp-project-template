/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
package org.mifos.testing.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mifos.core.common.di.AppDispatchers

@OptIn(ExperimentalCoroutinesApi::class)
val TestDispatchersModule: Module = module {
    single { TestCoroutineScheduler() }
    single<TestDispatcher> { UnconfinedTestDispatcher(scheduler = get()) }
    single<CoroutineDispatcher>(named(AppDispatchers.IO.name)) {
        StandardTestDispatcher(scheduler = get())
    }
    single<CoroutineDispatcher>(named(AppDispatchers.Default.name)) {
        StandardTestDispatcher(scheduler = get())
    }
    single<CoroutineDispatcher>(named(AppDispatchers.Unconfined.name)) {
        UnconfinedTestDispatcher(scheduler = get())
    }
}
