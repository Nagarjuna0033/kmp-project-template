package org.mifos.testing.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mifos.core.common.di.AppDispatchers

val TestDispatchersModule: Module = module {
    single<TestDispatcher> { UnconfinedTestDispatcher() }
    single<CoroutineDispatcher>(named(AppDispatchers.IO.name)) { StandardTestDispatcher() }
    single<CoroutineDispatcher>(named(AppDispatchers.Default.name)) { StandardTestDispatcher() }
    single<CoroutineDispatcher>(named(AppDispatchers.Unconfined.name)) { UnconfinedTestDispatcher() }
}
